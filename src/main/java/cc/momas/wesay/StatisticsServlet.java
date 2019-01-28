package cc.momas.wesay;

import cc.momas.wesay.util.CommonHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用于统计的类，目前只用于统计在线人数
 *
 * @author Sod-Momas
 */
@WebServlet(value = {"/online/count"}, name = "StatisticsServlet")
public class StatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("json/html");
        response.setCharacterEncoding("UTF-8");
        // 影响内容输出器
        PrintWriter out = response.getWriter();
        // 响应数据类型
        response.setContentType("application/json");
        out.print("{\"onlineCount\":" + CommonHelper.getOnlineCount() + "}");
        out.flush();
    }
}
