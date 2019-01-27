package cc.momas.wesay.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sod-Momas
 */
@ServerEndpoint(value = "/wesay")
public class WeSayEndPoint {

    private static final String GUEST_PREFIX = "用户";
    private static final AtomicInteger CONNECTION_IDS = new AtomicInteger(0);
    private static final Set<WeSayEndPoint> CONNECTIONS = new CopyOnWriteArraySet<>();

    private static final Logger log = LoggerFactory.getLogger(WeSayEndPoint.class);

    private String nickname;
    private Session session;

    public WeSayEndPoint() {
        nickname = GUEST_PREFIX + CONNECTION_IDS.getAndIncrement();
    }

    private static void broadcast(String msg) {
        for (WeSayEndPoint client : CONNECTIONS) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                log.warn("Chat Error: 与该客户端连接失败: {}", e.getMessage());
                CONNECTIONS.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("系统消息: %s %s", client.nickname, "已下线.");
                broadcast(message);
            }
        }
    }


    @OnOpen
    public void start(Session session) {
//        log.debug("user {} connected" , session.getRequestURI().getHost());
        this.session = session;
        CONNECTIONS.add(this);
        String message = String.format("* %s %s", nickname, "已上线.");
        broadcast(message);
    }

    @OnClose
    public void end() {
        CONNECTIONS.remove(this);
        String message = String.format("* %s %s", nickname, "已下线.");
        broadcast(message);
    }

    @OnMessage
    public void incoming(String message) {
        // Never trust the client
        final String renameFlag = "[rename]";
        if (message.startsWith(renameFlag)) {
            this.nickname = message.substring(message.indexOf(renameFlag));
        }
        String filteredMessage = String.format("%s: %s", nickname, message.toString());
        broadcast(filteredMessage);
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: {} ", t.getMessage());
    }

    public static int getOnlineCount() {
        return CONNECTIONS.size();
    }
}
