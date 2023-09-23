package dev.bharath.productservice.services;

import dev.bharath.productservice.dtos.GenericProductDto;
import dev.bharath.productservice.exceptions.NotFoundException;
import dev.bharath.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(Long id) throws NotFoundException;

    public GenericProductDto createProduct(GenericProductDto product);

    public List<GenericProductDto> getAllProducts();

    public GenericProductDto deleteProduct(Long id);
}
