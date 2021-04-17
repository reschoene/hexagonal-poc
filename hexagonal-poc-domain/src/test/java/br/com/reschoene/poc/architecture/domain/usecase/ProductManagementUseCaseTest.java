package br.com.reschoene.poc.architecture.domain.usecase;

import br.com.reschoene.poc.architecture.domain.model.Product;
import br.com.reschoene.poc.architecture.port.exception.ProductNotFoundException;
import br.com.reschoene.poc.architecture.port.output.repository.ProductRepositoryPort;
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
class ProductManagementUseCaseTest {
    @InjectMocks
    private ProductManagementUseCase productManagementUseCase;

    @Mock
    ProductRepositoryPort<Product> repositoryPort;

    @Test
    @DisplayName("getProductById returns a product when given product id exists")
    void getProductById_ReturnsProduct_WhenProductIdExist() throws ProductNotFoundException {
        BDDMockito.given(repositoryPort.getProductById(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(ProductCreator.createValid()));

        var product = productManagementUseCase.getProductById(1L);

        Assertions.assertThat(product)
                .isNotNull();
    }

    @Test
    @DisplayName("getProductById throws ProductNotFoundException when given product id exists")
    void getProductById_ThrowsProductNotFoundException_WhenProductIdExist() {
        Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(() -> productManagementUseCase.getProductById(-1L))
                .withMessageContaining("Product not found");
    }

    @Test
    @DisplayName("getAllProducts returns a product list when success")
    void getAllProducts_ReturnsProductList_WhenSuccess() {
        BDDMockito.given(repositoryPort.getAllProducts())
                .willReturn(List.of(ProductCreator.createValid()));

        var products = productManagementUseCase.getAllProducts();

        Assertions.assertThat(products)
                .isNotNull()
                .hasSize(1);
    }

    @Test
    @DisplayName("addProduct returns a valid product when success")
    void addProduct_ReturnsValidProduct_WhenSuccess() {
        var prodToBeSaved = ProductCreator.createToBeSaved();
        BDDMockito.given(repositoryPort.addProduct(prodToBeSaved))
                .willReturn(ProductCreator.createValid());

        var product = productManagementUseCase.addProduct(prodToBeSaved);

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getId())
                .isNotNull()
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("updateProduct returns a valid product when success")
    void updateProduct_ReturnsValidProduct_WhenSuccess() throws ProductNotFoundException {
        BDDMockito.given(repositoryPort.getProductById(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(ProductCreator.createValid()));

        var prodToBeUpdated = ProductCreator.createProductToBeUpdated();
        BDDMockito.given(repositoryPort.updateProduct(prodToBeUpdated))
                .willReturn(ProductCreator.createUpdatedProduct());

        var product = productManagementUseCase.updateProduct(prodToBeUpdated);

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
    @DisplayName("updateProduct throws ProductNotFoundException when given product id exists")
    void updateProduct_ThrowsProductNotFoundException_WhenProductIdExist() {
        Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(() -> productManagementUseCase.updateProduct(ProductCreator.createToBeSaved()))
                .withMessageContaining("Product not found");
    }

    @Test
    @DisplayName("removeProduct returns a valid product when success")
    void removeProduct_ReturnsValidProduct_WhenSuccess() throws ProductNotFoundException {
        var validProd = ProductCreator.createValid();
        BDDMockito.given(repositoryPort.getProductById(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(validProd));

        BDDMockito.given(repositoryPort.removeProduct(validProd))
                .willReturn(validProd);

        var product = productManagementUseCase.removeProduct(validProd);

        Assertions.assertThat(product)
                .isNotNull();

        Assertions.assertThat(product.getId())
                .isNotNull()
                .isEqualTo(1L);

        Assertions.assertThat(product.getDescription())
                .isNotNull()
                .isEqualTo(validProd.getDescription());
    }

    @Test
    @DisplayName("removeProduct throws ProductNotFoundException when given product id exists")
    void removeProduct_ThrowsProductNotFoundException_WhenProductIdExist() {
        Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(() -> productManagementUseCase.removeProduct(ProductCreator.createToBeSaved()))
                .withMessageContaining("Product not found");
    }
}
