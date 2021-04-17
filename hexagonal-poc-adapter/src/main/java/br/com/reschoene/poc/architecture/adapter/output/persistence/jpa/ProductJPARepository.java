package br.com.reschoene.poc.architecture.adapter.output.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {
}
