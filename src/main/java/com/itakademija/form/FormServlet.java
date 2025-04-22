package com.itakademija.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * <li>1. Preduvjet da budem Servlet </li>
 * <li>2. Preduvjet - je da budem izložen vanjskom svijetu
 *      <p>
 *          URL
 *          http://locahost:8080/web-1.0-SNAPSHOT/form
 *      </p>
 * </li>
 */
public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        if(req.getCharacterEncoding()==null){
            req.setCharacterEncoding("UTF-8");
        }
        try(PrintWriter out = resp.getWriter()){
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Form Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Form Servlet</h1>");
            String param = req.getParameter("text");
            out.println("<p>Poslali ste : "+param+"</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
