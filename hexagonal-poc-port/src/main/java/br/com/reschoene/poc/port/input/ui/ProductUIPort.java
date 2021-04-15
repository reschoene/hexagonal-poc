package br.com.reschoene.poc.port.input.ui;

import br.com.reschoene.poc.port.dto.ProductDto;

public interface ProductUIPort {
    Object getProductById(Long productId);
    Object getProducts();
    Object addProduct(ProductDto productDto);
    Object removeProduct(ProductDto productDto);
    Object updateProduct(ProductDto productDto);
}
