package cc.momas.wesay;

import cc.momas.wesay.util.CommonHelper;
import cc.momas.wesay.util.Message;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "weSayFilter",value = {"/"},servletNames = {"weSayServlet"})
public class WeSayFilter implements Filter {

    public void destroy() {
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        chain.doFilter(request, response);
    }

    public void init(javax.servlet.FilterConfig config) throws ServletException {
    }

}
