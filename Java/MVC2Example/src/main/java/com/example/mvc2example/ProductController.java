package com.example.mvc2example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet(name = "ProductController", value = "/ProductController")

public class ProductController extends HttpServlet {
    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Add new product
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addProduct(req, resp);
                break;
            case "edit":
            case "delete":
                deleteProduct(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null)
            action = "list"; //default return list of product
            switch (action) {
                case "list":
                    listProduct(req,resp);
                    break;
                case "delete":
                    deleteProduct(req,resp);
                    break;
        }
    }
    //bis coding here
    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Step 1 Call controller
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        //Step 2 Instance of model
        Product product = new Product(products.size() +1,name,price);
        products.add(product); // add to database call model add product here
        //resp.sendRedirect("product-detail.jsp");
        //Giao tiếp với các servlet khác và với view(jsp)
        resp.sendRedirect("ProductController?action=list"); // servlet communication

    }
    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i); // delete in database
                break;
            }
        }
        resp.sendRedirect("ProductController?action=list");
//        // Step 1: Get product ID from request
//        int id = Integer.parseInt(req.getParameter("id"));
//
//        // Step 2: Find and remove product by ID
//        Iterator<Product> iterator = products.iterator();
//        while (iterator.hasNext()) {
//            Product product = iterator.next();
//            if (product.getId() == id) {
//                iterator.remove();
//                break;
//
//            }
//        }
//        // Step 3: Redirect to product list
//        resp.sendRedirect("ProductController?action=list");
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Thiết lập thuộc tính trả về cho view từ conttroller (step 3)
        req.setAttribute("products", products);
        //Giao tiếp với các servlet khác và với view(jsp)
        //Servlet Comminication
        req.getRequestDispatcher("product.jsp").forward(req,resp);
    }
}
