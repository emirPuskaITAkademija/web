package com.itakademija.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JspFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String requestUri = httpRequest.getRequestURI();

        // Ako neko pokušava direktno pristupiti .jsp fajlu, preusmjeri na početnu stranicu
        if (requestUri.endsWith(".jsp")) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/errorPage.html");
            return; // Završava izvršavanje filtera
        }

        // Propušta ostale zahtjeve
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
