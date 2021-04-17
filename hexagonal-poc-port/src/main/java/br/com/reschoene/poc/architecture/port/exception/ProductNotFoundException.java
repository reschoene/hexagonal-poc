package br.com.reschoene.poc.architecture.port.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(){
        super("Product not found");
    }
}
