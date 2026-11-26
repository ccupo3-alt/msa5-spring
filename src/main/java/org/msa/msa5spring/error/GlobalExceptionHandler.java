package org.msa.msa5spring.error;

import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // ------------------------------------------------
    // 유효성 체크 예외 관련
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GlobalResponseDTO<String>> handle(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new GlobalResponseDTO<>(
                                "E01",
                                "유효성 체크 에러",
                                String.format("%s: 필드를 확인해 주세요.", e.getName())

                        )
                );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseDTO<Map<String, String>>> handle(MethodArgumentNotValidException e) {
        // e.getBindingResult()
        //         .getFieldErrors()
        //         .stream()
        //         .collect(Collectors.toMap());
        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                   FieldError::getField, // 필드명을 가져와서 map의 key로 사용
                        (fieldError) -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "유효하지 않은 값입니다.",
                        (existing, replacement) -> existing
                ));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new GlobalResponseDTO<>(
                                "E01",
                                "유효성 체크 에러",
                                errors
                        )
                )
    }
    // ------------------------------------------------
    // 그 외 모든 예외 처리 핸들러 메소드
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
