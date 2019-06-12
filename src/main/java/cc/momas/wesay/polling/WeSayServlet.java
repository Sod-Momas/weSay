package cc.momas.wesay.polling;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 程序核心
 *
 * @author Sod-Momas
 */
@WebServlet(name = "weSayServlet", value = {"/msg"}, displayName = "weSayServlet")
public class WeSayServlet extends HttpServlet {

    /**
     * 聊天记录存储器
     */
    private final static MessageList DATA_BASE = MessageList.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print(DATA_BASE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String content = request.getParameter("content");

        username = isNullOrEmpty(username) ? "匿名用户" : username;

        // 没有内容的消息要丢弃掉
        if (!isNullOrEmpty(content)) {
            Message msg = new Message(username, content);
            DATA_BASE.add(msg);
        }
    }

    /**
     * 判断数组中是否有空数据
     *
     * @param strings 数组
     * @return 数组中是否有空字符串, 有的话true, 否则false
     */
    private static boolean isNullOrEmpty(String... strings) {
        for (String string : strings) {
            if (string == null || string.trim().length() < 1) {
                return true;
            }
        }
        return false;
    }
}
