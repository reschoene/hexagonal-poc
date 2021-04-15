package br.com.reschoene.poc.architecture.output.persistence.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {
}
