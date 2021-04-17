package br.com.reschoene.poc.architecture.adapter.input.cli.controller;

import br.com.reschoene.poc.architecture.adapter.input.dto.ProductDto;
import br.com.reschoene.poc.architecture.domain.model.Product;
import br.com.reschoene.poc.architecture.port.exception.ProductNotFoundException;
import br.com.reschoene.poc.architecture.port.input.service.ProductServicePort;
import br.com.reschoene.poc.architecture.port.input.ui.ProductUIPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class ProductCliControllerAdapter implements ProductUIPort<ProductDto, ProductDto> {
    private final ProductServicePort<Product> productServicePort;

    @Override
    public ProductDto getProductById(Long productId) {
        ProductDto dto = null;
        try {
            var product = productServicePort.getProductById(productId);
            dto = ProductDto.toDto(product);
        } catch (ProductNotFoundException e) {
            log.debug("Product not found");
        }

        return dto;
    }

    @Override
    public List<ProductDto> getProducts() {
        var prodList = productServicePort.getAllProducts();
        return prodList.stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        var prod =  productServicePort.addProduct(ProductDto.fromDto(productDto));
        return ProductDto.toDto(prod);
    }

    @Override
    public ProductDto removeProduct(ProductDto productDto) {
        ProductDto dto = null;
        try {
            var product = productServicePort.removeProduct(ProductDto.fromDto(productDto));
            dto = ProductDto.toDto(product);

        } catch (ProductNotFoundException e) {
            log.debug(e.getMessage());
        }

        return dto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        ProductDto dto = null;
        try {
            var product = productServicePort.updateProduct(ProductDto.fromDto(productDto));
            dto = ProductDto.toDto(product);
        } catch (ProductNotFoundException e) {
            log.debug(e.getMessage());
        }

        return dto;
    }
}
