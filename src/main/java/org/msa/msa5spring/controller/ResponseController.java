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
    public ResponseEntity<GlobalResponseDTO<String>> index(){
        //바디에 주는거임, 바디의 타입
        // return ResponseEntity.status(200).body("3");
        //스프링이 인스턴스 안해도 리스폰스 엔티티는 자동생성 해줌
        //return ResponceEntiy.od("3");
        // return ResponseEntity.notFound().build()
        GlobalResponseDTO<String> globalResponseDTO = new GlobalResponseDTO<>("00", "정상 처리", "레스폰스 테스트");
        return ResponseEntity.status(200).body(globalResponseDTO);
    }

    @GetMapping("res/test")
    public ResponseEntity<GlobalResponseDTO<QueryRequestDTO>> test(
    ){
        return ResponseEntity.status(200).body(new GlobalResponseDTO<>(
                "00",
                "정상처리",
                new QueryRequestDTO(3L, "홍길동")
        ));

    }
}
