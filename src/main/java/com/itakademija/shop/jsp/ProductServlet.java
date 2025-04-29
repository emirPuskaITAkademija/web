package com.itakademija.shop.jsp;

import com.itakademija.shop.Product;
import com.itakademija.shop.ProductReader;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * <1> http://localhost:8080/web-1.0-SNAPSHOT/proizvodi   HttpServletRequest</1>
 * <2> CONTROLLER prima request = ProductServlet</2>
 * <3> Procesira request tako što mu(HttpServletRequest) stavlja pod ključem 'products' List<Product> = MODEL</3>
 * <4> Kontroller kaže evo ti MODEL da ga prikažeš = VIEW products.jsp</4>
 *
 *
 * CONTROLLER-Dispatcher Servlet
 */
@WebServlet(name = "productServlet", urlPatterns = {"/proizvodi"})
public class ProductServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * MODEL
         */

        List<Product> products = (List<Product>) getServletContext().getAttribute(PRODUCTS);
        request.setAttribute(PRODUCTS, products);

        /**
         * VIEW - je ono što prikazuje podatke prilikom obrade request
         */
        RequestDispatcher dispatcher = request.getRequestDispatcher("shop/products.jsp");
        dispatcher.include(request, response);
    }

}


