package cc.momas.wesay.polling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 应用程序监听器
 *
 * @author Sod-Momas
 */
@WebListener
public class WeSayListener implements HttpSessionListener {

    private final static Logger log = LoggerFactory.getLogger(WeSayListener.class);

    /**
     * 轮询在线人数
     */
    private static AtomicInteger pollingUserCounter = new AtomicInteger(0);
    /**
     * websocket在线人数
     */
    private static AtomicInteger websocketUserCount = new AtomicInteger(0);

    static int getPollingUserCounter() {
        return pollingUserCounter.get();
    }

    public static int getWebsocketUserCount() {
        return websocketUserCount.get();
    }

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        // 指定一分钟不发言为下线状态
        sessionEvent.getSession().setMaxInactiveInterval(60);
        // 用户上线,用于统计在线人数
        int count = pollingUserCounter.incrementAndGet();
        log.info("新用户上线,当前在线人数:{}", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        // 用户下线,用于统计在线人数
        int count = pollingUserCounter.decrementAndGet();
        log.info("用户下线,当前在线人数:{}", count);
    }
}
