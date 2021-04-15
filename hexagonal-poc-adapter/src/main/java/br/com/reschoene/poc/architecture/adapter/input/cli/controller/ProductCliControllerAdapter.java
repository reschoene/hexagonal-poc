package br.com.reschoene.poc.architecture.adapter.input.cli.controller;

import br.com.reschoene.poc.port.input.ui.ProductUIPort;
import br.com.reschoene.poc.port.dto.ProductDto;
import br.com.reschoene.poc.port.input.service.ProductServicePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductCliControllerAdapter implements ProductUIPort {
    private final ProductServicePort productServicePort;

    @Override
    public ProductDto getProductById(Long productId) {
        return productServicePort.getProductById(productId);
    }

    @Override
    public List<ProductDto> getProducts() {
        return productServicePort.getAllProducts();
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        productServicePort.addProduct(productDto);
        return productDto;
    }

    @Override
    public ProductDto removeProduct(ProductDto productDto) {
        productServicePort.removeProduct(productDto);
        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        productServicePort.updateProduct(productDto);
        return productDto;
    }
}
