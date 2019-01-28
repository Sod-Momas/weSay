package cc.momas.wesay;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 设置编码的过滤器
 *
 * @author Sod-Momas
 */
@WebFilter(filterName = "weSayFilter", value = {"/"}, servletNames = {"weSayServlet"})
public class WeSayFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        chain.doFilter(request, response);
    }

    @Override
    public void init(javax.servlet.FilterConfig config) {
    }

}
