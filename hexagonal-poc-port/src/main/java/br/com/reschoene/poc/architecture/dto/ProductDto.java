package br.com.reschoene.poc.architecture.dto;

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
}
