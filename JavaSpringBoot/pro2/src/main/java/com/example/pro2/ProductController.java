package com.example.pro2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    // Call model step2
    @Autowired
    private ProductRepository productRepository;

    // Step 1
    @GetMapping("/ ")
    public String productList(Model model) {
        List<Product> products = productRepository.findAll();
        // Step 3
        model.addAttribute("products", products);
        return "product-list";
    }

    // Hiển thị form thêm sản phẩm mới
    @GetMapping("/product/new")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-form";
    }

    // Xử lý yêu cầu thêm sản phẩm mới
    @PostMapping("/product")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/";
    }

    // Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/product/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-form";
        } else {
            return "redirect:/";
        }
    }

    // Xử lý yêu cầu cập nhật sản phẩm
    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product) {
        product.setId(id);
        productRepository.save(product);
        return "redirect:/";
    }

    // Xử lý yêu cầu xóa sản phẩm
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/";
    }
}
