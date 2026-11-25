package org.msa.msa5spring.error;

import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) // Exception만 적으면 단순히 타입을 말하는 거
    public ResponseEntity<GlobalResponseDTO<Void>> handle(Exception e) {
        // ArrayList<Integer> test = new ArrayList<>();
        return ResponseEntity.status(500).body(
                new GlobalResponseDTO<>(
                        "E99",
                        "시스템 에러",
                        null
                )
        );
    }
}
