package br.com.reschoene.poc.port.input.ui;

public interface ProductUIPort<T, U> {
    U getProductById(Long productId);
    Object getProducts();
    U addProduct(T productDto);
    U removeProduct(T productDto);
    U updateProduct(T productDto);
}
