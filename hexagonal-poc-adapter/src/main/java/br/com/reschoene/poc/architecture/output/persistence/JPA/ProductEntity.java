package br.com.reschoene.poc.architecture.output.persistence.JPA;

import br.com.reschoene.poc.architecture.dto.ProductDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String description;
    private String brand;
    private double price;
    private LocalDate expirationDate;
    private LocalDate manufacturingDate;

    public static ProductEntity fromDto(ProductDto dto){
        return ProductEntity
                .builder()
                .id(dto.getId())
                .code(dto.getCode())
                .description(dto.getDescription())
                .brand(dto.getBrand())
                .price(dto.getPrice())
                .expirationDate(dto.getExpirationDate())
                .manufacturingDate(dto.getManufacturingDate())
                .build();
    }
    public static ProductDto toDto(ProductEntity productEntity){
        return ProductDto
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