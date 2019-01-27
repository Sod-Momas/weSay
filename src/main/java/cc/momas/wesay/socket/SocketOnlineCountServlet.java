package cc.momas.wesay.socket;

import cc.momas.wesay.util.CommonHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = {"/online/count/socket"}, name = "StatisticsServlet")
public class SocketOnlineCountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // 影响内容输出器
        PrintWriter out = response.getWriter();
        // 响应数据类型
        response.setContentType("application/json");
        out.print("{\"onlineCount\":" + WeSayEndPoint.getOnlineCount() + "}");
        out.flush();
        out.close();
    }
}
