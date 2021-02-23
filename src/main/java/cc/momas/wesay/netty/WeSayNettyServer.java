package cc.momas.wesay.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.commons.cli.*;

/**
 * @author Sod-Momas
 * @since 2021-02-09
 */
public class WeSayNettyServer {

    public static void main(String[] args) throws ParseException {
        final var argLine = resoleArgs(args);
        startServer(argLine);
    }

    private static CommandLine resoleArgs(String[] args) throws ParseException {
        // create Options object
        var options = new Options();
        // add t option
        options.addOption("port", false, "server listener port");
        CommandLineParser parser = new DefaultParser();
        return parser.parse( options, args);
    }

    private static void startServer(CommandLine argLine) {
        final int port = getPort(argLine);
        final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final EventLoopGroup workerGroup = new NioEventLoopGroup();
        final ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        final ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new WesayServerHandler());
                    }
                });
        try {
            final ChannelFuture cf = bootstrap.bind(port).sync();
            System.out.println("netty server start at localhost:9000");
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("netty server closed");
        }
    }

    private static int getPort(CommandLine argLine) {
        final var port = argLine.getOptionValue("port","80");
        return Integer.parseInt(port);
    }

}
