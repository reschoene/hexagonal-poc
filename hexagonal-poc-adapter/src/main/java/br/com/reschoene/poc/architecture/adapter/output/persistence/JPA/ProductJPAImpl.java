package br.com.reschoene.poc.architecture.adapter.output.persistence.JPA;

import br.com.reschoene.poc.port.repository.ProductRepositoryPort;
import br.com.reschoene.poc.port.dto.ProductDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductJPAImpl implements ProductRepositoryPort {
    private final ProductJPARepository jpaRepository;

    @Override
    public ProductDto getProductById(Long productId) {
        return ProductEntity.toDto(jpaRepository.getOne(productId));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return jpaRepository.findAll()
                .stream()
                .map(ProductEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(ProductDto productDto) {
        var entity = ProductEntity.fromDto(productDto);
        jpaRepository.save(entity);
    }

    @Override
    public void removeProduct(ProductDto productDto) {
        jpaRepository.deleteById(productDto.getId());
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        var entity = ProductEntity.fromDto(productDto);
        jpaRepository.save(entity);
    }
}
