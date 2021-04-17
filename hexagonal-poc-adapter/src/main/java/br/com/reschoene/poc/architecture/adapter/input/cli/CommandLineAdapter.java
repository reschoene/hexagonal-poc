package br.com.reschoene.poc.architecture.adapter.input.cli;

import br.com.reschoene.poc.architecture.adapter.dto.ProductDto;
import br.com.reschoene.poc.architecture.adapter.input.cli.controller.ProductCliControllerAdapter;
import br.com.reschoene.poc.architecture.domain.model.Product;
import br.com.reschoene.poc.port.input.service.ProductServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CommandLineAdapter implements CommandLineRunner {
    private final ProductServicePort<Product> service;

    @Override
    public void run(String... args) {
        executeTest();
    }

    private void executeTest() {
        log.info("");
        log.info("------------- COMMAND LINE CLI TESTER ---------");
        log.info("");

        var controller = new ProductCliControllerAdapter(service);

        var prod = ProductDto.builder()
                .code("cds10101")
                .brand("colgate")
                .description("escova de dente")
                .price(8.90)
                .manufacturingDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(10))
                .build();

        log.info("Adding product {}...", prod.getCode());
        prod = controller.addProduct(prod);
        log.info("Product was successful saved (id = {})", prod.getId());

        log.info("Getting product by id (id = {})...", prod.getId());
        prod = controller.getProductById(prod.getId());
        log.info("Received product {}", prod.getCode());

        log.info("Updating product description...");
        prod.setDescription("escova de dente 2.0");
        controller.updateProduct(prod);
        prod = controller.getProductById(prod.getId());
        log.info("New product descrition: {}", prod.getDescription());

        log.info("Deleting product...");
        controller.removeProduct(prod);
        log.info("Product was successfully removed");
    }
}
