package br.com.reschoene.poc.architecture.port.input.ui;

public interface ProductUIPort<T, U> {
    U getProductById(long productId);
    Object getProducts();
    U addProduct(T productDto);
    U removeProduct(T productDto);
    U updateProduct(T productDto);
}
