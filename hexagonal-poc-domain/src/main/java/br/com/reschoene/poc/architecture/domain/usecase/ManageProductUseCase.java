package br.com.reschoene.poc.architecture.domain.usecase;

import br.com.reschoene.poc.architecture.domain.model.Product;
import br.com.reschoene.poc.port.exception.ProductNotFoundException;
import br.com.reschoene.poc.port.input.service.ProductServicePort;
import br.com.reschoene.poc.port.output.repository.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ManageProductUseCase implements ProductServicePort<Product> {
    private final ProductRepositoryPort<Product> productRepositoryPort;

    @Override
    public Product addProduct(Product product) {
        return productRepositoryPort.addProduct(product);
    }

    @Override
    public Product removeProduct(Product product) throws ProductNotFoundException {
        if (productRepositoryPort.getProductById(product.getId()).isEmpty())
            throw new ProductNotFoundException();

        return productRepositoryPort.removeProduct(product);
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        if (productRepositoryPort.getProductById(product.getId()).isEmpty())
            throw new ProductNotFoundException();

        return productRepositoryPort.updateProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.getAllProducts();
    }

    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
        return productRepositoryPort.getProductById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }
}
