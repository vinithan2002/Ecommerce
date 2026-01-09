package com.ecommerce.online.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/shop")
    public String shopPage() {
        return "shop-grid"; // NOT shop-grid.jsp
    }
}
