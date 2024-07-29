package org.example.springsercurewebservicev2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {
    public String publicEndpoint() {
        return "publicEndpoint";
    }

    @GetMapping("/employee")
    public String employeeEndpoint() {
        return "employeeEndpoint";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "adminEndpoint";
    }
}
