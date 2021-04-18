package br.com.reschoene.poc.architecture.adapter.output.persistence.jpa;

import br.com.reschoene.poc.architecture.domain.model.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Product")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private String description;
    private String brand;
    private double price;
    private LocalDate expirationDate;
    private LocalDate manufacturingDate;

    public static ProductEntity fromModel(Product model){
        return ProductEntity
                .builder()
                .id(model.getId())
                .code(model.getCode())
                .description(model.getDescription())
                .brand(model.getBrand())
                .price(model.getPrice())
                .expirationDate(model.getExpirationDate())
                .manufacturingDate(model.getManufacturingDate())
                .build();
    }
    public static Product toModel(ProductEntity productEntity){
        return Product
                .builder()
                .id(productEntity.getId())
                .code(productEntity.getCode())
                .description(productEntity.getDescription())
                .brand(productEntity.getBrand())
                .price(productEntity.getPrice())
                .expirationDate(productEntity.getExpirationDate())
                .manufacturingDate(productEntity.getManufacturingDate())
                .build();
    }
}