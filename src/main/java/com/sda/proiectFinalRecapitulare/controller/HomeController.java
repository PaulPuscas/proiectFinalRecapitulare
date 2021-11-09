package com.sda.proiectFinalRecapitulare.controller;

import com.sda.proiectFinalRecapitulare.dto.ProductDto;
import com.sda.proiectFinalRecapitulare.service.ProductService;
import com.sda.proiectFinalRecapitulare.validator.ProductDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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
    public String postAddItemPage(ProductDto productDto, BindingResult bindingResult, @RequestParam("productImage") MultipartFile multipartFile) {
        System.out.println("Am primit " + multipartFile);
        productDtoValidator.validate(productDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addItem";
        }
        productService.add(productDto, multipartFile);
        return "redirect:/addItem";//rulez redirect catre get la linia23 sa incarc obiectul
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        List<ProductDto> productDtoList = productService.getAllProductDtos();//apeleaza Service pt a-i da lista de produse
        model.addAttribute("products", productDtoList);//apoi o adauga pe model
        return "home";//acum este accesibil din home
    }

    @GetMapping("/item/{productId}")
    public String getProductPage(@PathVariable(value = "productId") String productId, Model model) {
        System.out.println("Am primit id=ul " + productId);
        Optional<ProductDto> optionalProductDto = productService.getProductDtoById(productId);
        if (!optionalProductDto.isPresent()) { //daca cutia nu e goala
            return "errorPage";
        }
        ProductDto productDto = optionalProductDto.get();//am despachetat cutia
        model.addAttribute("product", productDto);
        return "viewItem";
    }
}
