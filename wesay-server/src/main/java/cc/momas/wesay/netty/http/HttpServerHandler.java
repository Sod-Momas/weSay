package cc.momas.wesay.netty.http;

import cc.momas.wesay.netty.JsonUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * @author Sod-Momas
 * @since 2021-03-06
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final static List<String> messageList = new LinkedList<>();
    private final static Set<Channel> GROUP = new HashSet<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        GROUP.forEach(ch -> {
            Map<String, String> response = new HashMap<>();
            response.put("msg", "【" + ch.remoteAddress()+ "】 上线了");
            ch.writeAndFlush(Unpooled.copiedBuffer(JsonUtil.stringify(response).getBytes(StandardCharsets.UTF_8)));
        });
        GROUP.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        GROUP.remove(ctx.channel());
        GROUP.forEach(ch -> {
            Map<String, String> response = new HashMap<>();
            response.put("msg", "【" + ctx.channel().remoteAddress() + "】 下线了");
            ch.writeAndFlush(Unpooled.copiedBuffer(JsonUtil.stringify(response).getBytes(StandardCharsets.UTF_8)));
        });
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        //100 Continue
        if (HttpUtil.is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
            return;
        }
        // 获取请求的uri
        String uri = req.uri();
        switch (uri) {
            case "/msg":
                boardcastMsg(ctx, req);
                break;
            case "/online":
                sendOnlineCount(ctx);
                break;
            default:
                notFount(ctx);
        }
    }

    private void sendOnlineCount(ChannelHandlerContext ctx) {
        Map<String, Integer> map = new HashMap<>();
        map.put("msg", GROUP.size());
        FullHttpResponse response = toJsonResponse(JsonUtil.stringify(map));
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void notFount(ChannelHandlerContext ctx) {
        Map<String, String> map = new HashMap<>();
        map.put("msg", "not found");
        FullHttpResponse response = toJsonResponse(JsonUtil.stringify(map));
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void boardcastMsg(ChannelHandlerContext ctx, FullHttpRequest req) {
        String msg = req.content().toString(CharsetUtil.UTF_8);
        System.out.println(msg);
        messageList.add(msg);
        Map<String, String> map = new HashMap<>();
        map.put("msg", JsonUtil.stringify(messageList));
        FullHttpResponse response = toJsonResponse(JsonUtil.stringify(map));
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private FullHttpResponse toJsonResponse(String stringify) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(stringify, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=UTF-8");
        return response;
    }

}
