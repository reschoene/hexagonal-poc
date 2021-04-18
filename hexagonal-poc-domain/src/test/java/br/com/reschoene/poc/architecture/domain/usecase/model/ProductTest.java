package br.com.reschoene.poc.architecture.domain.usecase.model;

import br.com.reschoene.poc.architecture.domain.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ProductTest {
    @Test
    @DisplayName("when expiration date is closer to current date price has fifty percent discount")
    void when_ExpirationDateIsCloserToCurrentDate_PriceHasFiftyPerDiscount() {
        double originalPrice = 60.50;
        double discountPerProdCloseToExpireDate = 0.5;

        var prodCloseToExpireDate = Product.builder()
                .price(originalPrice)
                .expirationDate(LocalDate.now().plusDays(2))
                .build();

        double salePrice = originalPrice - (originalPrice * discountPerProdCloseToExpireDate);

        Assertions.assertThat(prodCloseToExpireDate.getPrice()).isEqualTo(salePrice);
    }

    @Test
    @DisplayName("when expiration date is far to current date price has ten percent discount")
    void when_ExpirationDateIsFarToCurrentDate_PriceHasFiftyPerDiscount() {
        double originalPrice = 60.50;
        double discountPerProdCloseToExpireDate = 0.1;

        var prodCloseToExpireDate = Product.builder()
                .price(originalPrice)
                .expirationDate(LocalDate.now().plusDays(20))
                .build();

        double salePrice = originalPrice - (originalPrice * discountPerProdCloseToExpireDate);

        Assertions.assertThat(prodCloseToExpireDate.getPrice()).isEqualTo(salePrice);
    }
}
