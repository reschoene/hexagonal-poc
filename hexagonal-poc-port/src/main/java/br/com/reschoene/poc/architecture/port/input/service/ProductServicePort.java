package br.com.reschoene.poc.architecture.port.input.service;

import br.com.reschoene.poc.architecture.port.exception.ProductNotFoundException;

import java.util.List;

public interface ProductServicePort<T> {
    T addProduct(T product);

    T removeProduct(T product) throws ProductNotFoundException;

    T updateProduct(T product) throws ProductNotFoundException;

    List<T> getAllProducts();

    T getProductById(Long productId) throws ProductNotFoundException;
}
