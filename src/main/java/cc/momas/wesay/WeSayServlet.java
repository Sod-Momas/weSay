package cc.momas.wesay;

import cc.momas.wesay.util.CommonHelper;
import cc.momas.wesay.util.Message;
import cc.momas.wesay.util.MessageList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    private static MessageList dataBase = new MessageList();

    static {
        //用来生成系统第一条消息
        dataBase.add(new Message("系统消息", "还没有消息哦,快来聊天吧!"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print(dataBase.toJson());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String content = request.getParameter("content");
        username = CommonHelper.isNullOrEmpty(username) ? "匿名用户" : username;
        if (!CommonHelper.isNullOrEmpty(content)) {
            Message msg = new Message(username, content);
            dataBase.add(msg);
        }
    }
}
