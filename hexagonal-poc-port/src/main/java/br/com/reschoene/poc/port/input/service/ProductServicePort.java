package br.com.reschoene.poc.port.input.service;

import br.com.reschoene.poc.port.exception.ProductNotFoundException;

import java.util.List;

public interface ProductServicePort<T> {
    T addProduct(T product);

    T removeProduct(T productDto) throws ProductNotFoundException;

    T updateProduct(T productDto) throws ProductNotFoundException;

    List<T> getAllProducts();

    T getProductById(Long productId) throws ProductNotFoundException;
}
