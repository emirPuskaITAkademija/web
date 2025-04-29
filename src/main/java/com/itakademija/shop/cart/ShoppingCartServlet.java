package com.itakademija.shop.cart;

import com.itakademija.shop.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.MissingResourceException;

import static com.itakademija.shop.WebShopServlet.PRODUCTS;

@WebServlet(name = "shoppingCartServlet", urlPatterns = {"/cart"})
public class ShoppingCartServlet extends HttpServlet {

    public static final String CART = "cart";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Shopping Cart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Shopping Cart</h1>");
            HttpSession session = request.getSession();
            ShoppingCart cart = (ShoppingCart) session.getAttribute(CART);
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute(CART, cart);
            }
            String[] productIds = request.getParameterValues("productId");
            String[] quantities = request.getParameterValues("quantity");
            for (int i = 0; i < productIds.length; i++) {
                String productIdText = productIds[i];
                String quantityText = quantities[i];
                if (quantityText.isEmpty()) {
                    continue;
                }
                int quantity = Integer.parseInt(quantityText);
                int productId = Integer.parseInt(productIdText);
                List<Product> products = (List<Product>) getServletContext().getAttribute(PRODUCTS);
                Product product = products
                        .stream()
                        .filter(p -> p.getId() == productId)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                cart.addItem(product, quantity);
            }
            if (cart.getItems() == null || cart.getItems().isEmpty()) {
                out.println("<h1> Korpa je prazna </h1>");
            } else {
                out.println("<h1> Korpa </h1>");
                out.println("<table>");
                out.println("<tr bgcolor='lightgray'><th>Naziv</th><th>Jedinična Cijena</th><th>Quantity</th><th>Ukupna Cijena</th></tr>");
                for (ShoppingCartItem item : cart.getItems()) {
                    out.println("""
                            <tr>
                              <td>%s</td>
                              <td>%s</td>
                              <td>%s</td>
                              <td>%s</td>
                            </tr>
                            """.formatted(item.getProduct().getName(),
                            item.getProduct().getPrice(),
                            item.getQuantity(),
                            item.getTotalPrice()));
                }
                out.println("</table>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
