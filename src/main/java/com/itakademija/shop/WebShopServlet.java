package com.itakademija.shop;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * URL: http://localhost:8080/web-1.0-SNAPSHOT/products
 */
@WebServlet(name = "webShopServlet", urlPatterns = {"/products"})
public class WebShopServlet extends HttpServlet {

    public static final String PRODUCTS = "products";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        List<Product> products = (List<Product>) servletContext.getAttribute(PRODUCTS);
        if (products == null) {
            String path = servletContext.getRealPath("WEB-INF/products.txt");
            products = new ProductReader(path).read();
            servletContext.setAttribute(PRODUCTS, products);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Available Products</title>");
            out.println("</head>");
            out.println("<body>");
            List<Product> products = (List<Product>) getServletContext().getAttribute(PRODUCTS);
            if(products != null && !products.isEmpty()) {
                out.println("<h1>Available Products</h1>");
                out.println("<table border='1'>");
                out.println("<tr><th>Naziv</th><th>Cijena</th><th>Korpa</th></tr>");
                for(Product product : products) {
                    out.println("<tr>");
                    out.println("<td>" + product.getName()+"</td>");
                    out.println("<td>"+product.getPrice()+"</td>");
                    out.println("<td>");
                    out.println("""
                            <form action="cart" method=GET>
                                <input type='number' name='quantity' size='4'/>
                                <input type='hidden' value='%s' name='productId'/>
                      
                            """.formatted(product.getId()));
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("""
                            <input type='submit' value='Dodaj'/>
                            </form>
                            """);
            }else{
                out.println("<h1>Trenutno nismo u mogućnosti prikazati aritkle u našem web shopu</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}
