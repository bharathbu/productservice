package dev.bharath.productservice.controllers;

import dev.bharath.productservice.dtos.ExceptionDTO;
import dev.bharath.productservice.dtos.GenericProductDto;
import dev.bharath.productservice.exceptions.NotFoundException;
import dev.bharath.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException{
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
        //return "Created a new Product with name : " + product.getTitle();
    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}
