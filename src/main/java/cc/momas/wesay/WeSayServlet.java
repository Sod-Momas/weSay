package cc.momas.wesay;

import cc.momas.wesay.util.CommonHelper;
import cc.momas.wesay.util.Message;
import cc.momas.wesay.util.MsgList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "weSayServlet", value = {"/msg"}, displayName = "weSayServlet")
public class WeSayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * 聊天记录最大数
     */
    private static final int MESSAGE_SIZE = 100;
    /**
     * 聊天记录存储器
     */
//    private static LimitedList<Message> msgList = new LimitedList<Message>(MESSAGE_SIZE);
    private static MsgList msgs = new MsgList(MESSAGE_SIZE);

    static {//用来生成系统第一条消息
//        msgList.add(new Message("系统消息", "还没有消息哦,快来聊天吧!"));
        msgs.add(new Message("系统消息", "还没有消息哦,快来聊天吧!"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String json = msgs.toJson();
//        String json = JSON.toJSONString(msgList.getList());
        // 输出
        out.print(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 影响内容输出器
        PrintWriter out = response.getWriter();
        // 取参数
        String username = request.getParameter("username");
        String content = request.getParameter("content");
        username = CommonHelper.isNullOrEmpty(username) ? "匿名用户" : username;
        if (!CommonHelper.isNullOrEmpty(content)) {
            Message msg = new Message(username, content);
//            msgList.add(msg);
            msgs.add(msg);
            out.write("{\"success\":\"success\"}");
        }
    }
}
