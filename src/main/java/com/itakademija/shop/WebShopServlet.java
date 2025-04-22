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
            products = new ArrayList<>();
            String path = servletContext.getRealPath("WEB-INF/products.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] lineParts = line.split(";");
                    Long id = Long.parseLong(lineParts[0]);
                    String name = lineParts[1];
                    double price = Double.parseDouble(lineParts[2]);
                    Product product = new Product(id, name, price);
                    products.add(product);
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
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
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            ServletContext servletContext = getServletContext();
            List<Product> products = (List<Product>) servletContext.getAttribute(PRODUCTS);
            out.println("Ukupno " + products.size());
        }
    }
}
