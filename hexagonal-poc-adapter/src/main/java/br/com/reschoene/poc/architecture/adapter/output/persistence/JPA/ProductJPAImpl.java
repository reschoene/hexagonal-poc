package br.com.reschoene.poc.architecture.adapter.output.persistence.JPA;

import br.com.reschoene.poc.port.repository.ProductRepositoryPort;
import br.com.reschoene.poc.port.dto.ProductDto;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductJPAImpl implements ProductRepositoryPort {
    private final ProductJPARepository jpaRepository;

    @Override
    public Optional<ProductDto> getProductById(Long productId) {
        var optEntity = jpaRepository.findById(productId);
        if(optEntity.isPresent())
            return Optional.of(ProductEntity.toDto(optEntity.get()));
        else
            return Optional.empty();
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return jpaRepository.findAll()
                .stream()
                .map(ProductEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        var entity = ProductEntity.fromDto(productDto);
        return ProductEntity.toDto(jpaRepository.save(entity));
    }

    @Override
    public ProductDto removeProduct(ProductDto productDto) {
        jpaRepository.deleteById(productDto.getId());
        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        var entity = ProductEntity.fromDto(productDto);
        return ProductEntity.toDto(jpaRepository.save(entity));
    }
}
