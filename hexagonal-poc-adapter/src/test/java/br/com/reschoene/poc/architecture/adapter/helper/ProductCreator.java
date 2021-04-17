package br.com.reschoene.poc.architecture.adapter.helper;

import br.com.reschoene.poc.architecture.domain.model.Product;

import java.time.LocalDate;

public class ProductCreator {
    public static Product createValid(){
        var prod = createToBeSaved();
        prod.setId(1L);
        return prod;
    }

    public static Product createToBeSaved() {
        return Product.builder()
                .brand("brand")
                .code("code01")
                .description("short description")
                .price(60.90)
                .expirationDate(LocalDate.now().plusDays(10))
                .manufacturingDate(LocalDate.now())
                .build();
    }

    public static Product createProductToBeUpdated() {
        return Product.builder()
                .id(1L)
                .description("short description updated")
                .build();
    }

    public static Product createUpdatedProduct() {
        var prod = createValid();
        prod.setDescription("short description updated");
        return prod;
    }
}
