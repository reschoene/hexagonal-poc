package br.com.reschoene.poc.architecture.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Builder
@Data
public class Product {
    private Long id;
    private String code;
    private String description;
    private String brand;
    private double price;
    private LocalDate expirationDate;
    private LocalDate manufacturingDate;

    public double getPrice(){
        long daysBetween = ChronoUnit.DAYS.between(expirationDate, manufacturingDate);

        double discountPerc = (daysBetween <= 5 ? 0.5 : 0.1);

        return price - (price * discountPerc);
    }
}
