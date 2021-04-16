package br.com.reschoene.poc.architecture.adapter.input.cli.controller;

import br.com.reschoene.poc.port.exception.ProductNotFoundException;
import br.com.reschoene.poc.port.dto.ProductDto;
import br.com.reschoene.poc.port.input.service.ProductServicePort;
import br.com.reschoene.poc.port.input.ui.ProductUIPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductCliControllerAdapter implements ProductUIPort {
    private final ProductServicePort productServicePort;

    @Override
    public ProductDto getProductById(Long productId) {
        ProductDto productDto = null;
        try {
            productDto = productServicePort.getProductById(productId);
        } catch (ProductNotFoundException e) {
            System.out.println("Product not found");
        }

        return productDto;
    }

    @Override
    public List<ProductDto> getProducts() {
        return productServicePort.getAllProducts();
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return productServicePort.addProduct(productDto);
    }

    @Override
    public ProductDto removeProduct(ProductDto productDto) {
        try {
            productDto = productServicePort.removeProduct(productDto);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        try {
            productDto = productServicePort.updateProduct(productDto);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return productDto;
    }
}
