package br.com.reschoene.poc.architecture.adapter.config;

import br.com.reschoene.poc.architecture.adapter.output.persistence.jpa.ProductJPAAdapter;
import br.com.reschoene.poc.architecture.adapter.output.persistence.jpa.ProductJPARepository;
import br.com.reschoene.poc.architecture.domain.model.Product;
import br.com.reschoene.poc.architecture.domain.usecase.ProductManagementUseCase;
import br.com.reschoene.poc.port.input.service.ProductServicePort;
import br.com.reschoene.poc.port.output.repository.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
    private final ProductJPARepository productJPARepository;

    @Bean
    public ProductServicePort<Product> productServicePort(){
        return new ProductManagementUseCase(productRepositoryPort());
    }

    @Bean
    public ProductRepositoryPort<Product> productRepositoryPort(){
        return new ProductJPAAdapter(productJPARepository);
    }
}
