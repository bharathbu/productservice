package dev.bharath.productservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id){
        return "Here is the product id: " + id;
    }

    @DeleteMapping("{id}")
    public void deleteProductById(){

    }

    @PostMapping
    public void createProduct(){

    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}
