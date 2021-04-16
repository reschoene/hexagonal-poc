package br.com.reschoene.poc.architecture.adapter.input.rest.error;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ResponseError {
    private final String errorMessage;
}
