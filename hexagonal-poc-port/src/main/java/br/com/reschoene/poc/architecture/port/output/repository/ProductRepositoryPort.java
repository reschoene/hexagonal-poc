package br.com.reschoene.poc.architecture.port.output.repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort<T> {
    T addProduct(T product);

    T removeProduct(T product);

    T updateProduct(T product);

    List<T> getAllProducts();

    Optional<T> getProductById(Long productId);
}
