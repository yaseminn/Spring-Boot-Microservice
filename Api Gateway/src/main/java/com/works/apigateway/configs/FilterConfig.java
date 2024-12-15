package com.works.apigateway.configs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;
import brave.Tracer;


@Configuration
public class FilterConfig implements Filter {

    final Tracer tracer;
    public FilterConfig(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String spanId = tracer.currentSpan().context().spanIdString();
        String traceId = tracer.currentSpan().context().traceIdString();

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

        res.setHeader("spanId", spanId);
        res.setHeader("traceId", traceId);

        filterChain.doFilter(req, res);
    }
}
