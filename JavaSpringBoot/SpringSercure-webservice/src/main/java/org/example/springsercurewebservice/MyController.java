package org.example.springsercurewebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {
    public String publicEndpoint() {
        return "public Endpoint";
    }

    @GetMapping("/secure")
    public String secureEndpoint() {
        return "You need Authenticated to access! SECURE Endpoint";
    }
}
