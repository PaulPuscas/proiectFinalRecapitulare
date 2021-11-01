package com.sda.proiectFinalRecapitulare.controller;

import com.sda.proiectFinalRecapitulare.dto.ProductDto;
import com.sda.proiectFinalRecapitulare.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/addItem")
    public String getAddItemPage(Model model) {
        //in aceasta met. adaugam cod, cand cineva face Request pe addItem iar la final intoarce un addItem

        //Handler=este metoda getAddItemPage si este apelata de DispatcherServlet
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "addItem";
    }
    @PostMapping("/addItem")
    public String postAddItemPage( ProductDto productDto) {
        System.out.println("Am primit " + productDto);
        productService.add(productDto);
        return "addItem";
        }
}
