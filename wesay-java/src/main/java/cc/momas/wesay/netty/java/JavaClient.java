package cc.momas.wesay.netty.java;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author Sod-Momas
 * @since 2021-03-09
 */
public class JavaClient {
    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        final ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) {
                                System.out.println("连接服务器成功");
                            }

                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }

                            @Override
                            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                super.channelInactive(ctx);
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                System.err.println("服务器挂了");
                            }
                        });
                    }
                });

        try {
            final WeSayEnvironment env = new WeSayEnvironment(args);
            final int port = env.getPort();
            final String host = env.getHost();
            System.out.println("attempt to connect " + host + ':' + port + "...");
            final ChannelFuture cf = bootstrap.connect(host, port).sync();
            final Channel channel = cf.channel();
            System.out.println("netty client start, my address is " + channel.localAddress() + ". (type 'exit' to exit)");
            final Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                channel.writeAndFlush(line);
                if ("exit".equals(line)) {
                    System.out.println("closing client...");
                    channel.close().sync();
                    break;
                }
            }
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
            System.out.println("bye.");
        }
    }
}
