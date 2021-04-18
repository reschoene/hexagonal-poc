package br.com.reschoene.poc.architecture.adapter.persistence;

import br.com.reschoene.poc.architecture.adapter.helper.ProductCreator;
import br.com.reschoene.poc.architecture.adapter.helper.ProductEntityCreator;
import br.com.reschoene.poc.architecture.adapter.output.persistence.jpa.ProductEntity;
import br.com.reschoene.poc.architecture.adapter.output.persistence.jpa.ProductJPAAdapter;
import br.com.reschoene.poc.architecture.adapter.output.persistence.jpa.ProductJPARepository;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductJPAAdapterTest {
    @InjectMocks
    private ProductJPAAdapter productJPAAdapter;

    @Mock
    ProductJPARepository jpaRepository;

    @Test
    @DisplayName("getProductById returns a product when given product id exists")
    void getProductById_ReturnsProduct_WhenProductIdExist() {
        BDDMockito.given(jpaRepository.findById(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(ProductEntityCreator.createValid()));

        var product = productJPAAdapter.getProductById(1L);

        Assertions.assertThat(product)
                .isNotNull();
    }

    @Test
    @DisplayName("getProductById returns an empty optional when given product id exists")
    void getProductById_ReturnsEmptyOptional_WhenProductIdDoesNotExist() {
        var product = productJPAAdapter.getProductById(-1L);

        Assertions.assertThat(product)
                .isEmpty();
    }

    @Test
    @DisplayName("getAllProducts returns a product list when success")
    void getAllProducts_ReturnsProductList_WhenSuccess() {
        BDDMockito.given(jpaRepository.findAll())
                .willReturn(List.of(ProductEntityCreator.createValid()));

        var products = productJPAAdapter.getAllProducts();

        Assertions.assertThat(products)
                .isNotNull()
                .hasSize(1);
    }

    @Test
    @DisplayName("addProduct returns a valid product when success")
    void addProduct_ReturnsValidProduct_WhenSuccess() {
        BDDMockito.given(jpaRepository.save(ProductEntity.fromModel(ProductCreator.createToBeSaved())))
                .willReturn(ProductEntityCreator.createValid());

        var product = productJPAAdapter.addProduct(ProductCreator.createToBeSaved());

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getId())
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("updateProduct returns a valid product when success")
    void updateProduct_ReturnsValidProduct_WhenSuccess() {
        var prodToBeUpdated = ProductEntityCreator.createProductToBeUpdated();
        BDDMockito.given(jpaRepository.save(prodToBeUpdated))
                .willReturn(ProductEntityCreator.createUpdatedProduct());

        var product = productJPAAdapter.updateProduct(ProductCreator.createProductToBeUpdated());

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getId())
                .isEqualTo(1L);

        Assertions.assertThat(product.getDescription())
                .isNotNull()
                .isEqualTo(prodToBeUpdated.getDescription());
    }

    @Test
    @DisplayName("removeProduct returns a valid product when success")
    void removeProduct_ReturnsValidProduct_WhenSuccess() {
        var product = productJPAAdapter.removeProduct(ProductCreator.createValid());

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getId())
                .isEqualTo(1L);

        Assertions.assertThat(product.getDescription())
                .isEqualTo(ProductCreator.createValid().getDescription());
    }
}

