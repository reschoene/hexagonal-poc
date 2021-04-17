package br.com.reschoene.poc.port.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(){
        super("Product not found");
    }
}
