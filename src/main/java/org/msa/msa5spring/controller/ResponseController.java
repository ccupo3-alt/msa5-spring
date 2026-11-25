package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResponseController {
    @GetMapping("/res")
    public ResponseEntity<GlobalResponseDTO<String>> index() {
        // return ResponseEntity.status(200).body("3");
        // return ResponseEntity.ok("3");
        // return ResponseEntity.notFound().build();
        GlobalResponseDTO<String> globalResponseDTO = new GlobalResponseDTO<>(
                "00",
                "정상처리",
                "response test"
        );

        return ResponseEntity.status(200).body(globalResponseDTO);
    }

    @GetMapping("/res/test")
    public ResponseEntity<GlobalResponseDTO<QueryRequestDTO>> test(
            QueryRequestDTO queryRequestDTO
    ) {
        return ResponseEntity.status(200).body(
                new GlobalResponseDTO<>( // <> 안에 QueryRequestDTO가 기본으로 들어있음
                        "00",
                        "정상 처리",
                        new QueryRequestDTO(3L, "홍길동")
                )
        );
    }

}
