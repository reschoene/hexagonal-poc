package br.com.reschoene.poc.architecture.domain.usecase;

import br.com.reschoene.poc.port.dto.ProductDto;
import br.com.reschoene.poc.port.exception.ProductNotFoundException;
import br.com.reschoene.poc.port.input.service.ProductServicePort;
import br.com.reschoene.poc.port.repository.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ManageProductUseCase implements ProductServicePort {
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return productRepositoryPort.addProduct(productDto);
    }

    @Override
    public ProductDto removeProduct(ProductDto productDto) throws ProductNotFoundException {
        if (productRepositoryPort.getProductById(productDto.getId()).isEmpty())
            throw new ProductNotFoundException("Product not found");

        return productRepositoryPort.removeProduct(productDto);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) throws ProductNotFoundException {
        if (productRepositoryPort.getProductById(productDto.getId()).isEmpty())
            throw new ProductNotFoundException("Product not found");

        return productRepositoryPort.updateProduct(productDto);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepositoryPort.getAllProducts();
    }

    @Override
    public ProductDto getProductById(Long productId) throws ProductNotFoundException {
        return productRepositoryPort.getProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }
}
