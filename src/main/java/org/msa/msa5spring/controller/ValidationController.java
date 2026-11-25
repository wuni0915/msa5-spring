package org.msa.msa5spring.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.msa.msa5spring.request.RegistrationRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ValidationController {
    //컨트롤러는 클래스
    @GetMapping("/validation")
    public String validation(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO){
        return "유효성 검사";
        //내가 사용할 메소드에서 반드시 밸리드 어노테이션 넣어주기
    }

    @GetMapping("/validation/{id}")
    public String validationSegment (
       @Min(value = 1, message = "1이상") @PathVariable Long id
    ){
        return id.toString();
        //리턴타입이 머든 간에 스트링 리스폰스 객체로 바꾸고 리스포스 헤드와 바디로 바꾸어 변환
    }
}
