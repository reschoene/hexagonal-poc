package br.com.reschoene.poc.architecture.adapter.input.rest.controller;

import br.com.reschoene.poc.architecture.adapter.input.dto.ProductDto;
import br.com.reschoene.poc.architecture.adapter.input.rest.error.ResponseError;
import br.com.reschoene.poc.architecture.domain.model.Product;
import br.com.reschoene.poc.architecture.port.exception.ProductNotFoundException;
import br.com.reschoene.poc.architecture.port.input.service.ProductServicePort;
import br.com.reschoene.poc.architecture.port.input.ui.ProductUIPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ProductRestControllerAdapter implements ProductUIPort<ProductDto, ResponseEntity<?>> {
    private final ProductServicePort<Product> productServicePort;

    @Override
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        Product prod;
        try {
            prod = productServicePort.getProductById(productId);
        }
        catch (ProductNotFoundException e){
            return new ResponseEntity<>(new ResponseError(e.getMessage()), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ProductDto.toDto(prod), HttpStatus.OK);
    }

    @Override
    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getProducts() {
        var prodList = productServicePort.getAllProducts();
        var prodDtoList
                = prodList.stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(prodDtoList, HttpStatus.OK);
    }

    @Override
    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        var product = productServicePort.addProduct(ProductDto.fromDto(productDto));
        return new ResponseEntity<>(ProductDto.toDto(product), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/product")
    public ResponseEntity<?> removeProduct(@RequestBody ProductDto productDto) {
        Product product;
        try {
            product = productServicePort.removeProduct(ProductDto.fromDto(productDto));
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getMessage()), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ProductDto.toDto(product), HttpStatus.OK);
    }

    @Override
    @PutMapping("/product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto) {
        Product product;
        try {
            product = productServicePort.updateProduct(ProductDto.fromDto(productDto));
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getMessage()), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ProductDto.toDto(product), HttpStatus.OK);
    }
}