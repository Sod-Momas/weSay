package cc.momas.wesay.polling;

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
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String charset = "UTF-8";
        String contentType = "application/json";

        request.setCharacterEncoding(charset);

        chain.doFilter(request, response);

        response.setCharacterEncoding(charset);
        response.setContentType(contentType);
    }

    @Override
    public void destroy() {
    }
}
