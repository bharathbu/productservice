package dev.bharath.productservice.thirdpartclients.productsservice.fakestore;

import dev.bharath.productservice.dtos.GenericProductDto;
import dev.bharath.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Wrapper over FakeStore API, Hence it should return Fake Store DTO
 */
@Service
public class FakeStoryProductServiceClient{
    private RestTemplateBuilder restTemplateBuilder;

    @Value("fakestore.api.url")
    private String fakeStoreApiUrl;

    @Value("fakestore.api.paths.product")
    private String fakeStoreProductsApiPath;

    private String specificProductRequestURL;

    private String productRequestBaseURL;

    @Autowired
    public FakeStoryProductServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.specificProductRequestURL= fakeStoreApiUrl+fakeStoreProductsApiPath+"/{id}";
        this.productRequestBaseURL= fakeStoreApiUrl + fakeStoreProductsApiPath;
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestURL, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id: "+ id+ " doesn't exist");
        }
        return response.getBody();
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestBaseURL,product,FakeStoreProductDto.class);
        return response.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseURL,FakeStoreProductDto[].class);

        return Arrays.stream(response.getBody()).toList();
    }

    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return response.getBody();
    }
}
