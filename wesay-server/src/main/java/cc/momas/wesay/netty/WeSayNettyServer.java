package cc.momas.wesay.netty;

import cc.momas.wesay.netty.plaintext.PlainTextInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sod-Momas
 * @since 2021-02-09
 */
public class WeSayNettyServer {
    private static WeSayEnvironment env;
    private static final Logger log = LoggerFactory.getLogger(WeSayNettyServer.class);

    public static void main(String[] args) {
        log.info("server starting...");
        System.setProperty("io.netty.noUnsafe", Boolean.TRUE.toString());
        System.setProperty("io.netty.tryUnsafe", Boolean.FALSE.toString());
        env = new WeSayEnvironment(args);
        startServer();
    }

    private static void startServer() {
        final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final EventLoopGroup workerGroup = new NioEventLoopGroup();
        final ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new PlainTextInitializer())
//                .childHandler(new HttpServerInitializer())
        ;
        try {
            final ChannelFuture cf = bootstrap.bind(env.getPort()).sync();
            log.info("netty server start at localhost:{}", env.getPort());
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            log.info("server shutdown,bye.");
        }
    }
}
