package br.com.reschoene.poc.port.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductNotFoundException extends Exception{
    @Getter private final String message;
}
