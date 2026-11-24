package org.msa.msa5spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//rest란 api를 만들때 필요한것들은 자동 세팅/ 클래스다라고 알려주는 어노테이션
//레스트시스템이냐 일반적인 서버사이더렌더링이냐에 따라 어노테이션이 달라진다.
//내가 지금 만든 클래스가 컨트롤러라고 인지시켜주는 어노테이션/ 이거 필수임~!!
public class RequestMappingController {
    @GetMapping("/users") //연결하는거임
    //유저가 단순조회 http메소드는 먼가로 올거임 get/users 로 왔을때 반환
    public String index (){
        return "유저 조회";
        //스프링부트는 8080 로컬호스트로 열림 http://localhost:8080/users
    }

    @PostMapping("/users/1")
    public String store(){
        return "유저 생성";
    }
    //url 검색은 기본적으로 get이기때문에 브라우저에서 확인이 안됨 이런거때문에 postman을 쓴다.
    //get에 /users/1로 감< 그래서 안됨 >
}
