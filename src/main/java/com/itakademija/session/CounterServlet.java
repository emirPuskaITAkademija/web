package com.itakademija.session;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "counterServlet", urlPatterns = {"/counter"})
public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CounterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Session Manager</h1>");
            /**
             * Ovim kodom ispod brojim posjete od pojedinačnih korisnika
             */
            //od Joze došao request - Jozina sesija
            //od Tarika došao request - Tarikova sesija
            //od Jovana došao request - Jovanova sesija
            //od Dženite došao request - Dženita sesija
            HttpSession session = request.getSession();
            SessionCounter sessionCounter = (SessionCounter) session.getAttribute("sessionCounter");
            if (sessionCounter == null) {
                sessionCounter = new SessionCounter();
                session.setAttribute("sessionCounter", sessionCounter);
            }
            sessionCounter.increment();
            Thread thread = Thread.currentThread();
            out.println("<h5>Thread name = " + thread.getName() + "</h2>");
            out.println("<h5>Cookie ID = " + request.getCookies()[0].getValue() + "</h5>");
            out.println("<h5>Broj posjeta od '" + session.getId() + "' = " + sessionCounter.getCounter() + "</h5>");
            /**
             * Ukupan broj posjeta pristiglih od svih korisnika. Dženita/Jozo/Tarik/Jovan
             */
            ServletContext servletContext = getServletContext();
            Counter counter = (Counter) servletContext.getAttribute("servletContextCounter");
            if (counter == null) {
                counter = new Counter();
                servletContext.setAttribute("servletContextCounter", counter);
            }
            counter.increment();
            out.printf("<h5>Ukupan broj posjeta = " + counter.getCounter() + "</h5>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
