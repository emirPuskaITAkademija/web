package com.itakademija.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Koji je preduvjet da neku klasu smatramo Servletom ?
 * <li>
 *     1. extends HttpServlet
 * </li>
 * <li>
 *     2. budem exposed na nekom URL
 *     <li>2.1 web.xml - deployment descriptor  </li>
 *     <li>2.2</li>
 * </li>
 */
public class FirstServlet extends HttpServlet {

    //http://locahost:8080/web-1.0-SNAPSHOT/prvi  GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Vozdra raja");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
