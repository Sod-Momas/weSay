package cc.momas.wesay.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Sod-Momas
 * @since 2021-02-09
 */
public class WesayServerHandler extends SimpleChannelInboundHandler<String> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    /**
     * QQ群
     */
    private static final ChannelGroup GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        String msg = "[ " + ctx.channel().remoteAddress() + " ] 上线了";
        log.info(msg);
        GROUP.writeAndFlush(msg);
        // 新用户进群
        GROUP.add(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 群员发消息
        final var channel = ctx.channel();

        GROUP.forEach(ch -> {
            final String displayName;
            if (ch == channel) {
                displayName = "[ 自己 ]";
            } else {
                displayName = "[ " + ch.remoteAddress() + " ]";
            }

            ch.writeAndFlush(displayName + now() + "\r\n" + msg);
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        // 群员退群
        GROUP.remove(ctx.channel());
        String msg = "[ " + ctx.channel().remoteAddress() + " ] 没了";
        log.info(msg);
        GROUP.writeAndFlush(msg);
    }
    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
