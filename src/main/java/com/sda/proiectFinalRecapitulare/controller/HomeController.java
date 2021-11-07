package com.sda.proiectFinalRecapitulare.controller;

import com.sda.proiectFinalRecapitulare.dto.ProductDto;
import com.sda.proiectFinalRecapitulare.service.ProductService;
import com.sda.proiectFinalRecapitulare.validator.ProductDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDtoValidator productDtoValidator;

    @GetMapping("/addItem")
    public String getAddItemPage(Model model) {
        //in aceasta met. adaugam cod, cand cineva face Request pe addItem iar la final intoarce un addItem
        System.out.println("Rulez get pe /addItem");
        //Handler=este metoda getAddItemPage si este apelata de DispatcherServlet
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "addItem";
    }

    @PostMapping("/addItem")
    public String postAddItemPage(ProductDto productDto, BindingResult bindingResult) {
        System.out.println("Am primit " + productDto);
        productDtoValidator.validate(productDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addItem";
        }
        productService.add(productDto);
        return "redirect:/addItem";//rulez redirect catre get la linia23 sa incarc obiectul
    }

}
