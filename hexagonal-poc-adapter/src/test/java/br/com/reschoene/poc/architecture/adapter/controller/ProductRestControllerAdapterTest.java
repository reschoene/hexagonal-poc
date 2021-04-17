package br.com.reschoene.poc.architecture.adapter.controller;

import br.com.reschoene.poc.architecture.adapter.helper.ProductCreator;
import br.com.reschoene.poc.architecture.adapter.helper.ProductDtoCreator;
import br.com.reschoene.poc.architecture.adapter.input.dto.ProductDto;
import br.com.reschoene.poc.architecture.adapter.input.rest.controller.ProductRestControllerAdapter;
import br.com.reschoene.poc.architecture.adapter.input.rest.error.ResponseError;
import br.com.reschoene.poc.architecture.domain.model.Product;
import br.com.reschoene.poc.architecture.port.exception.ProductNotFoundException;
import br.com.reschoene.poc.architecture.port.input.service.ProductServicePort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductRestControllerAdapterTest {
    @InjectMocks
    private ProductRestControllerAdapter productRestControllerAdapter;

    @Mock
    ProductServicePort<Product> productServicePort;

    @Test
    @DisplayName("getProductById returns a product when given product id exists")
    void getProductById_ReturnsProduct_WhenProductIdExist() throws ProductNotFoundException {
        BDDMockito.given(productServicePort.getProductById(ArgumentMatchers.anyLong()))
                .willReturn(ProductCreator.createValid());

        var product = productRestControllerAdapter.getProductById(1L);

        Assertions.assertThat(product)
                .isNotNull();
    }

    @Test
    @DisplayName("getProductById returns status code 204 when given product id does not exists")
    void getProductById_ReturnsStatusCode204_WhenProductIdDoesNotExist() throws ProductNotFoundException {
        BDDMockito.given(productServicePort.getProductById(ArgumentMatchers.anyLong()))
                .willThrow(ProductNotFoundException.class);

        var res = productRestControllerAdapter.getProductById(-1L);

        Assertions.assertThat(res)
                .isNotNull();

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(res.getBody())
                .isNotNull()
                .isInstanceOf(ResponseError.class);
    }

    @Test
    @DisplayName("getAllProducts returns a product list when success")
    void getAllProducts_ReturnsProductList_WhenSuccess() {
        BDDMockito.given(productServicePort.getAllProducts())
                .willReturn(List.of(ProductCreator.createValid()));

        var products = productRestControllerAdapter.getProducts();

        Assertions.assertThat(products)
                .isNotNull();

        Assertions.assertThat(products.getBody())
                .isNotNull()
                .hasSize(1);
    }

    @Test
    @DisplayName("addProduct returns a valid product when success")
    void addProduct_ReturnsValidProduct_WhenSuccess() {
        var prodToBeSaved = ProductCreator.createValid();
        BDDMockito.given(productServicePort.addProduct(prodToBeSaved))
                .willReturn(ProductCreator.createValid());

        var product = productRestControllerAdapter.addProduct(ProductDtoCreator.createValid());

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getBody())
                .isNotNull();

        Assertions.assertThat(product.getBody().getId())
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("updateProduct returns a valid product when success")
    void updateProduct_ReturnsValidProduct_WhenSuccess() throws ProductNotFoundException {
        var prodToBeUpdated = ProductCreator.createProductToBeUpdated();
        BDDMockito.given(productServicePort.updateProduct(prodToBeUpdated))
                .willReturn(ProductCreator.createUpdatedProduct());

        var product = productRestControllerAdapter.updateProduct(ProductDtoCreator.createProductToBeUpdated());

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getBody())
                .isNotNull();

        Assertions.assertThat(((ProductDto)product.getBody()).getId())
                .isEqualTo(1L);

        Assertions.assertThat(((ProductDto)product.getBody()).getDescription())
                .isNotNull()
                .isEqualTo(prodToBeUpdated.getDescription());
    }

    @Test
    @DisplayName("removeProduct returns a valid product when success")
    void removeProduct_ReturnsValidProduct_WhenSuccess() throws ProductNotFoundException {
        var validProd = ProductCreator.createValid();

        BDDMockito.given(productServicePort.removeProduct(validProd))
                .willReturn(validProd);

        var product = productRestControllerAdapter.removeProduct(ProductDtoCreator.createValid());

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getBody())
                .isNotNull();

        Assertions.assertThat(((ProductDto)product.getBody()).getId())
                .isEqualTo(1L);

        Assertions.assertThat(((ProductDto)product.getBody()).getDescription())
                .isNotNull()
                .isEqualTo(validProd.getDescription());
    }
}
