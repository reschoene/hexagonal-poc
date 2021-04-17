package br.com.reschoene.poc.architecture.adapter;

import br.com.reschoene.poc.architecture.adapter.input.cli.controller.ProductCliControllerAdapter;
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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductCliControllerAdapterTest {
    @InjectMocks
    private ProductCliControllerAdapter productCliControllerAdapter;

    @Mock
    ProductServicePort<Product> productServicePort;

    @Test
    @DisplayName("getProductById returns a product when given product id exists")
    void getProductById_ReturnsProduct_WhenProductIdExist() throws ProductNotFoundException {
        BDDMockito.given(productServicePort.getProductById(ArgumentMatchers.anyLong()))
                .willReturn(ProductCreator.createValid());

        var product = productCliControllerAdapter.getProductById(1L);

        Assertions.assertThat(product)
                .isNotNull();
    }

    @Test
    @DisplayName("getAllProducts returns a product list when success")
    void getAllProducts_ReturnsProductList_WhenSuccess() {
        BDDMockito.given(productServicePort.getAllProducts())
                .willReturn(List.of(ProductCreator.createValid()));

        var products = productCliControllerAdapter.getProducts();

        Assertions.assertThat(products)
                .isNotNull()
                .hasSize(1);
    }

    @Test
    @DisplayName("addProduct returns a valid product when success")
    void addProduct_ReturnsValidProduct_WhenSuccess() {
        var prodToBeSaved = ProductCreator.createValid();
        BDDMockito.given(productServicePort.addProduct(prodToBeSaved))
                .willReturn(ProductCreator.createValid());

        var product = productCliControllerAdapter.addProduct(ProductDtoCreator.createValid());

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getId())
                .isNotNull()
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("updateProduct returns a valid product when success")
    void updateProduct_ReturnsValidProduct_WhenSuccess() throws ProductNotFoundException {
        var prodToBeUpdated = ProductCreator.createProductToBeUpdated();
        BDDMockito.given(productServicePort.updateProduct(prodToBeUpdated))
                .willReturn(ProductCreator.createUpdatedProduct());

        var product = productCliControllerAdapter.updateProduct(ProductDtoCreator.createProductToBeUpdated());

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getId())
                .isNotNull()
                .isEqualTo(1L);

        Assertions.assertThat(product.getDescription())
                .isNotNull()
                .isEqualTo(prodToBeUpdated.getDescription());
    }

    @Test
    @DisplayName("removeProduct returns a valid product when success")
    void removeProduct_ReturnsValidProduct_WhenSuccess() throws ProductNotFoundException {
        var validProd = ProductCreator.createValid();

        BDDMockito.given(productServicePort.removeProduct(validProd))
                .willReturn(validProd);

        var product = productCliControllerAdapter.removeProduct(ProductDtoCreator.createValid());

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getId())
                .isNotNull()
                .isEqualTo(1L);

        Assertions.assertThat(product.getDescription())
                .isNotNull()
                .isEqualTo(validProd.getDescription());
    }
}
