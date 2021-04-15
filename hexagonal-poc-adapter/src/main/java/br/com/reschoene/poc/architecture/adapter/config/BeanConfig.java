package br.com.reschoene.poc.architecture.adapter.config;

import br.com.reschoene.poc.port.input.service.ProductServicePort;
import br.com.reschoene.poc.architecture.adapter.output.persistence.JPA.ProductJPAImpl;
import br.com.reschoene.poc.architecture.adapter.output.persistence.JPA.ProductJPARepository;
import br.com.reschoene.poc.port.repository.ProductRepositoryPort;
import br.com.reschoene.poc.architecture.domain.usecase.ManageProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Autowired
    private ProductJPARepository productJPARepository;

    @Bean
    public ProductServicePort productServicePort(){
        return new ManageProductUseCase(productRepositoryPort());
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(){
        return new ProductJPAImpl(productJPARepository);
    }
}
