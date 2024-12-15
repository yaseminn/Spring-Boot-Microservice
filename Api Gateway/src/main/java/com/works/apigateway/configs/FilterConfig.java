package com.works.apigateway.configs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;
@Configuration
public class FilterConfig implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        String sessionID = req.getSession().getId();
        String header = req.getHeader("user-agent");
        String name = "";
        String roles = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            name = auth.getName();
            roles = auth.getAuthorities().toString();
        }
        String time = ""+System.currentTimeMillis();
        System.out.println(url + "-" + sessionID + "-" + header + "-" + name + "-"+roles+"-"+time);
        filterChain.doFilter(req, res);
    }
}
