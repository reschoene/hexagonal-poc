package br.com.reschoene.poc.architecture.adapter.input.cli;

import br.com.reschoene.poc.port.input.ui.ProductUIPort;
import br.com.reschoene.poc.port.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class CommandLineAdapter implements CommandLineRunner {
    private final ProductUIPort controller;

    @Override
    public void run(String... args) throws Exception {
//        var prod = ProductDto.builder()
//                .code("cds10101")
//                .brand("colgate")
//                .description("escova de dente")
//                .price(8.90)
//                .manufacturingDate(LocalDate.now())
//                .expirationDate(LocalDate.now().plusDays(10))
//                .build();
//
//        System.out.println(String.format("Adding product %s...", prod.getCode()));
//        prod = (ProductDto) controller.addProduct(prod);
//        System.out.println(String.format("Product was successful saved (id = %d)", prod.getId()));
//
//        System.out.println(String.format("Getting product by id (id = %d)...", prod.getId()));
//        prod = (ProductDto) controller.getProductById(prod.getId());
//        System.out.println(String.format("Received product %s", prod.getCode()));
//
//        System.out.println("Updating product description...");
//        prod.setDescription("escova de dente 2.0");
//        controller.updateProduct(prod);
//        prod = (ProductDto) controller.getProductById(prod.getId());
//        System.out.println(String.format("New product descrition: %s", prod.getDescription()));
//
//        System.out.println("Deleting product...");
//        controller.removeProduct(prod);
//        prod = (ProductDto) controller.getProductById(prod.getId());
//        System.out.println("Product was successfully deleted");
    }
}
