package br.com.reschoene.poc.architecture.output.repository;

import br.com.reschoene.poc.architecture.dto.ProductDto;

import java.util.List;

public interface ProductRepositoryPort {
    void addProduct(ProductDto productDto);

    void removeProduct(ProductDto productDto);

    void updateProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long productId);
}
