package br.com.reschoene.poc.architecture.port;

import br.com.reschoene.poc.architecture.port.exception.ProductNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PortTest {
    @Test
    @DisplayName("when ProductNotFound is thrown its text is correct")
    void whenProductNotFoundIsThrown_ItsTextIsCorrect() {
        Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(this::throwException)
                .withMessageContaining("Product not found");
    }

    private void throwException() throws ProductNotFoundException{
        throw new ProductNotFoundException();
    }
}
