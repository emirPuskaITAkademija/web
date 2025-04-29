package com.itakademija.shop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ProductReader {
    private final String filePath;

    public ProductReader(String filePath) {
        this.filePath = filePath;
    }

    public List<Product> read() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Product product = transformLineToProduct(line);
                products.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return products;
    }

    private Product transformLineToProduct(String line) {
        String[] lineParts = line.split(";");
        Long id = Long.parseLong(lineParts[0]);
        String name = lineParts[1];
        double price = Double.parseDouble(lineParts[2]);
         return new Product(id, name, price);
    }
}
