package br.com.reschoene.poc.architecture.adapter.helper;

import br.com.reschoene.poc.architecture.adapter.output.persistence.jpa.ProductEntity;
import br.com.reschoene.poc.architecture.domain.model.Product;

import java.time.LocalDate;

public class ProductEntityCreator {
    public static ProductEntity createValid(){
        var prod = createToBeSaved();
        prod.setId(1L);
        return prod;
    }

    public static ProductEntity createToBeSaved() {
        return ProductEntity.builder()
                .brand("brand")
                .code("code01")
                .description("short description")
                .price(60.90)
                .expirationDate(LocalDate.now().plusDays(10))
                .manufacturingDate(LocalDate.now())
                .build();
    }

    public static ProductEntity createProductToBeUpdated() {
        return ProductEntity.builder()
                .id(1L)
                .description("short description updated")
                .build();
    }

    public static ProductEntity createUpdatedProduct() {
        var prod = createValid();
        prod.setDescription("short description updated");
        return prod;
    }
}
