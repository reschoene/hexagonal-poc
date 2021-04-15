package br.com.reschoene.poc.architecture.usecase;

import br.com.reschoene.poc.architecture.dto.ProductDto;
import br.com.reschoene.poc.architecture.input.service.ProductServicePort;
import br.com.reschoene.poc.architecture.output.repository.ProductRepositoryPort;
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
