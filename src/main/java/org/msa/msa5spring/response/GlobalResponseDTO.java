package org.msa.msa5spring.response;

public record GlobalResponseDTO<T>(
        String code,
        String message,
        T result
) {
}
