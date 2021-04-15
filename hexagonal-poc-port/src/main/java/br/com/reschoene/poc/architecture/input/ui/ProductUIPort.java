package br.com.reschoene.poc.architecture.input.ui;

import br.com.reschoene.poc.architecture.dto.ProductDto;

public interface ProductUIPort {
    Object addProduct(ProductDto productDto);
    Object removeProduct(ProductDto productDto);
    Object updateProduct(ProductDto productDto);
    Object getProductById(Long productId);
    Object getProducts();
}
