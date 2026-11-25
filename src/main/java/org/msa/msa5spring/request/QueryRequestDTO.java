package org.msa.msa5spring.request;

import org.springframework.web.bind.annotation.GetMapping;

public record QueryRequestDTO(
        //데이터 타입에 따라 롱을 쓸지 뭘 쓸지 보면됨
    Long id,
    //Long는 객체형 롱이고 long는 기본형임 기본형은 널 허용 안함 long으로 하면 에러

    String name
        //필드명과 키명이 같아야된다.
        //백이 이렇게 명세서를 주면 프론트가 어떤식으로 줄지 정해서 준다?

) {
}
