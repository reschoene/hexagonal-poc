package br.com.reschoene.poc.port.input.service;

import br.com.reschoene.poc.port.dto.ProductDto;
import br.com.reschoene.poc.port.exception.ProductNotFoundException;

import java.util.List;

public interface ProductServicePort {
    ProductDto addProduct(ProductDto productDto);

    ProductDto removeProduct(ProductDto productDto) throws ProductNotFoundException;

    ProductDto updateProduct(ProductDto productDto) throws ProductNotFoundException;

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long productId) throws ProductNotFoundException;
}
