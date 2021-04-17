package br.com.reschoene.poc.architecture.adapter;

import br.com.reschoene.poc.architecture.adapter.input.dto.ProductDto;

import java.time.LocalDate;

public class ProductDtoCreator {
    public static ProductDto createValid(){
        var prod = createToBeSaved();
        prod.setId(1L);
        return prod;
    }

    public static ProductDto createToBeSaved() {
        return ProductDto.builder()
                .brand("brand")
                .code("code01")
                .description("short description")
                .price(60.90)
                .expirationDate(LocalDate.now().plusDays(10))
                .manufacturingDate(LocalDate.now())
                .build();
    }

    public static ProductDto createProductToBeUpdated() {
        return ProductDto.builder()
                .id(1L)
                .description("short description updated")
                .build();
    }

    public static ProductDto createUpdatedProduct() {
        var prod = createValid();
        prod.setDescription("short description updated");
        return prod;
    }
}
