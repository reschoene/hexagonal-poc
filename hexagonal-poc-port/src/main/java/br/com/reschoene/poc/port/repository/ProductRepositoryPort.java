package br.com.reschoene.poc.port.repository;

import br.com.reschoene.poc.port.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    ProductDto addProduct(ProductDto productDto);

    ProductDto removeProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    Optional<ProductDto> getProductById(Long productId);
}
