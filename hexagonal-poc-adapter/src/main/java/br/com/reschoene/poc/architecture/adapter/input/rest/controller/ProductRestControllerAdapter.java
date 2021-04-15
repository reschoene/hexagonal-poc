package br.com.reschoene.poc.architecture.adapter.input.rest.controller;

import br.com.reschoene.poc.port.dto.ProductDto;
import br.com.reschoene.poc.port.input.service.ProductServicePort;
import br.com.reschoene.poc.port.input.ui.ProductUIPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductRestControllerAdapter implements ProductUIPort {
    private final ProductServicePort productServicePort;

    @Override
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        ProductDto prod;
        try {
            prod = productServicePort.getProductById(productId);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @Override
    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getProducts() {
        var prodList = productServicePort.getAllProducts();
        return new ResponseEntity<>(prodList, HttpStatus.OK);
    }

    @Override
    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        productServicePort.addProduct(productDto);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/product")
    public ResponseEntity<ProductDto> removeProduct(@RequestBody ProductDto productDto) {
        productServicePort.removeProduct(productDto);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @Override
    @PutMapping("/product")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        productServicePort.updateProduct(productDto);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}