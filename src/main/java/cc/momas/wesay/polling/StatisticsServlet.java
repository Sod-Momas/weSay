package cc.momas.wesay.polling;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于统计的类，目前只用于统计在线人数
 *
 * @author Sod-Momas
 */
@WebServlet(value = {"/online/count"}, name = "StatisticsServlet")
public class StatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 影响内容输出器
        response.getWriter()
                .print("{\"onlineCount\":" + WeSayListener.getPollingUserCounter() + "}");
    }
}
