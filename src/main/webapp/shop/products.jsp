<%@ page import="com.itakademija.shop.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Available Products</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            text-align: center;
        }
        h1 {
            color: #2c3e50;
        }
        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #3498db;
            color: white;
            font-size: 18px;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        input[type="number"] {
            width: 60px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            text-align: center;
        }
        input[type="submit"] {
            background-color: #2ecc71;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 5px;
            font-weight: bold;
        }
        input[type="submit"]:hover {
            background-color: #27ae60;
        }
    </style>
</head>
<body>
<h1>🌟 Available Products 🌟</h1>

<table>
    <tr><th>Naziv</th><th>Cijena</th><th>Korpa</th></tr>
    <%
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
    %>
    <tr>
        <td><%= product.getName() %></td>
        <td><%= product.getPrice() %> €</td>
        <td>
            <form action="cart" method="GET">
                <input type='number' name='quantity' min="1" max="10"/>
                <input type='hidden' value='<%= product.getId() %>' name='productId'/>
                <input type='submit' value='Dodaj'/>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="3">🚫 Trenutno nema dostupnih proizvoda.</td></tr>
    <%
        }
    %>
</table>
</body>
</html>