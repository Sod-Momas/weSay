package cc.momas.wesay;

import cc.momas.wesay.util.CommonHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 应用程序监听器
 *
 * @author Sod-Momas
 */
@WebListener
public class WeSayListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        // 指定一分钟不发言为下线状态
        sessionEvent.getSession().setMaxInactiveInterval(60);
        CommonHelper.userLogin();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        CommonHelper.userLogout();
    }
}
