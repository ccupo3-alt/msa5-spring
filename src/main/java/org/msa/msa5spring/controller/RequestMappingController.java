package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api") // 이렇게 한 번에 설정해두면 각각 ("/api")를 따로 입력하지 않아도 됨
public class RequestMappingController {
    @GetMapping
    public String index() {
        return "유저 조회";
    }

    @PostMapping
    public String store() {
        return "유저 생성";
    }

    @PutMapping
    public String update() {
        return "유저 수정";
    }

    @DeleteMapping
    public String destroy() {
        return "유저 삭제";
    }

    @GetMapping("/query")
    public String query(QueryRequestDTO queryRequestDTO) {
        return String.format("%d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }

    @GetMapping("/segment/{id}/{name}")
    public String segment(@ModelAttribute QueryRequestDTO queryRequestDTO) {
        return String.format("segment = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }

    // segment parameter를 이용한 path와 겹치지 않도록 주의
    @GetMapping("/segment/1/my")
    public String test() {
        return "Test"; // id가 1이고 name이 my면 "Test"가 출력됨 (오류)
    }

    @PostMapping("/form")
    public String form(@ModelAttribute MultipartFile file) {
        return String.format("%s : %s", file.getContentType(), file.getOriginalFilename());
    }

    @GetMapping("/json")
    public String json(@RequestBody QueryRequestDTO queryRequestDTO) {
        return String.format("json = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }
}

// Query Parameter 방식
// - 어노테이션 X, DTO 객체 생성

// Segment parameter 방식
// - @PathVariable, 각 키에 맞는 Parameter를 별도로 Mapping Method(Controller)의 Parameter로 생성
// - @ModelAttribute, DTO 객체 생성

// Form Data 방식
// - @ModelAttribute, DTO 객체 또는 Controller Parameter

// JSON 방식
// - @RequestBody, DTO 객체