package br.com.reschoene.poc.architecture.adapter.persistence;

import br.com.reschoene.poc.architecture.adapter.helper.ProductEntityCreator;
import br.com.reschoene.poc.architecture.adapter.output.persistence.jpa.ProductJPARepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@RequiredArgsConstructor
class ProductJPARepositoryTest {
    @Autowired
    private ProductJPARepository productJPARepository;

    @Test
    @DisplayName("FindById retrieves a product when successful")
    void findById_RetrievesProduct_WhenSuccessful(){
        var product = ProductEntityCreator.createToBeSaved();
        var createdProduct = this.productJPARepository.save(product);

        var productOpt = this.productJPARepository.findById(createdProduct.getId());

        Assertions.assertThat(productOpt).isPresent();
        Assertions.assertThat(productOpt.get().getId()).isEqualTo(product.getId());
        Assertions.assertThat(productOpt.get().getBrand()).isEqualTo(product.getBrand());
        Assertions.assertThat(productOpt.get().getDescription()).isEqualTo(product.getDescription());
    }

    @Test
    @DisplayName("FindById retrieves optional empty when product id was not found")
    void findById_RetrievesOptionalEmpty_WhenProductWasNotFound(){
        var productOpt = this.productJPARepository.findById(-1L);

        Assertions.assertThat(productOpt).isEmpty();
    }

    @Test
    @DisplayName("Save creates product when successful")
    void save_CreateMovie_WhenSuccessful(){
        var product = ProductEntityCreator.createToBeSaved();
        var createdProduct = this.productJPARepository.save(product);

        Assertions.assertThat(createdProduct).isNotNull();
        Assertions.assertThat(createdProduct.getId()).isNotNull();
        Assertions.assertThat(createdProduct.getBrand()).isEqualTo(product.getBrand());
        Assertions.assertThat(createdProduct.getDescription()).isEqualTo(product.getDescription());
    }

    @Test
    @DisplayName("Save updates product when successful")
    void save_UpdatesMovie_WhenSuccessful(){
        var product = ProductEntityCreator.createToBeSaved();
        var createdProduct = this.productJPARepository.save(product);

        createdProduct.setDescription("New Description");
        var updatedMovie = this.productJPARepository.save(createdProduct);

        Assertions.assertThat(updatedMovie).isNotNull();
        Assertions.assertThat(updatedMovie.getId()).isNotNull();
        Assertions.assertThat(updatedMovie.getDescription()).isEqualTo(createdProduct.getDescription());
    }

    @Test
    @DisplayName("Delete removes product when successful")
    void delete_RemovesMovie_WhenSuccessful(){
        var product = ProductEntityCreator.createToBeSaved();
        var createdProduct = this.productJPARepository.save(product);

        this.productJPARepository.delete(createdProduct);

        var productOptional = this.productJPARepository.findById(createdProduct.getId());
        Assertions.assertThat(productOptional).isEmpty();
    }
}
