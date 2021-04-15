package br.com.reschoene.poc.architecture.domain.usecase;

import br.com.reschoene.poc.port.repository.ProductRepositoryPort;
import br.com.reschoene.poc.port.dto.ProductDto;
import br.com.reschoene.poc.port.input.service.ProductServicePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ManageProductUseCase implements ProductServicePort {
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public void addProduct(ProductDto productDto) {
        productRepositoryPort.addProduct(productDto);
    }

    @Override
    public void removeProduct(ProductDto productDto) {
        productRepositoryPort.removeProduct(productDto);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        productRepositoryPort.updateProduct(productDto);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepositoryPort.getAllProducts();
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return productRepositoryPort.getProductById(productId);
    }
}
