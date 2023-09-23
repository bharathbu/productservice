package dev.bharath.productservice.services;

import dev.bharath.productservice.thirdpartclients.productsservice.fakestore.FakeStoreProductDto;
import dev.bharath.productservice.dtos.GenericProductDto;
import dev.bharath.productservice.exceptions.NotFoundException;
import dev.bharath.productservice.thirdpartclients.productsservice.fakestore.FakeStoryProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoryProductServiceClient fakeStoryProductServiceClient;

    private GenericProductDto getGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription((fakeStoreProductDto.getDescription()));
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }
    @Autowired
    public FakeStoreProductService(FakeStoryProductServiceClient fakeStoryProductServiceClient){
        this.fakeStoryProductServiceClient = fakeStoryProductServiceClient;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return getGenericProductDto(fakeStoryProductServiceClient.getProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return getGenericProductDto(fakeStoryProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto :fakeStoryProductServiceClient.getAllProducts()) {
            genericProductDtos.add(getGenericProductDto(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return getGenericProductDto(fakeStoryProductServiceClient.deleteProduct(id));
    }


}
