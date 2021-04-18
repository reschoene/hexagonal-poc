package br.com.reschoene.poc.architecture.adapter.output.persistence.jpa;

import br.com.reschoene.poc.architecture.domain.model.Product;
import br.com.reschoene.poc.architecture.port.output.repository.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductJPAAdapter implements ProductRepositoryPort<Product> {
    private final ProductJPARepository jpaRepository;

    @Override
    public Optional<Product> getProductById(Long productId) {
        var optEntity = jpaRepository.findById(productId);
        return optEntity.map(ProductEntity::toModel);
    }

    @Override
    public List<Product> getAllProducts() {
        return jpaRepository.findAll()
                .stream()
                .map(ProductEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Product addProduct(Product product) {
        var entity = ProductEntity.fromModel(product);
        return ProductEntity.toModel(jpaRepository.save(entity));
    }

    @Override
    public Product removeProduct(Product product) {
        jpaRepository.deleteById(product.getId());
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        return addProduct(product);
    }
}
