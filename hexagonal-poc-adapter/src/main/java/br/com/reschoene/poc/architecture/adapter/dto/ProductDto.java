package br.com.reschoene.poc.architecture.adapter.dto;

import br.com.reschoene.poc.architecture.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String code;
    private String description;
    private double price;
    private String brand;
    private LocalDate expirationDate;
    private LocalDate manufacturingDate;

    public static Product fromDto(ProductDto dto){
        return Product
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
    public static ProductDto toDto(Product product){
        return ProductDto
                .builder()
                .id(product.getId())
                .code(product.getCode())
                .description(product.getDescription())
                .brand(product.getBrand())
                .price(product.getPrice())
                .expirationDate(product.getExpirationDate())
                .manufacturingDate(product.getManufacturingDate())
                .build();
    }
}
