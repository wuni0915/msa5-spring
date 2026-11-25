package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
//프리 픽스로 경로로 users를 가짐
//rest란 api를 만들때 필요한것들은 자동 세팅/ 클래스다라고 알려주는 어노테이션
//레스트시스템이냐 일반적인 서버사이더렌더링이냐에 따라 어노테이션이 달라진다.
//내가 지금 만든 클래스가 컨트롤러라고 인지시켜주는 어노테이션/ 이거 필수임~!!
public class RequestMappingController {
    @GetMapping //연결하는거임
    //유저가 단순조회 http메소드는 먼가로 올거임 get/users 로 왔을때 반환
    public String index (){
        return "유저 조회";
        //스프링부트는 8080 로컬호스트로 열림 http://localhost:8080/users
    }

    @PostMapping
    public String store(){
        return "유저 생성";
    }

    @PutMapping
    public String update(){
        return "유저 수정";
    }

    @DeleteMapping
    public String destory(){
        return "유저 삭제";
    }
    //url 검색은 기본적으로 get이기때문에 브라우저에서 확인이 안됨 이런거때문에 postman을 쓴다.
    //get에 /users/1로 감< 그래서 안됨 >
    //GET /users/1은 개발자가 문서에 적는 표기법
    @GetMapping("/query")
    public String query(QueryRequestDTO queryRequestDTO){
        return String.format("%d : %s", queryRequestDTO.id(), queryRequestDTO.name());
        //받기전에 알아야할것
        //클라이언트에서 데이터를 전달하는 방법이 뭐가 있을까
        //?key = 어쩌고 쿼리파라미터 방식임 또는 users/1 을 자원데이터로서 인식 시키는 방법(세그먼트파라미터)
        //패스 중 일부 패스를 데이터로 인식
        //백 프론트 얏속으로 두번째 영역에 있는 값을 id로 받겠다로 약속한 상황(세그먼트 파라미터)
        //3번째 방법 폼데이터(헤드{대가리}와 바디{출력용})바디부분에 세팅되어서 리퀘스트가 감(고객눈에 안빔)
        //파일전송과 관련된 처리를 할 때 폼데이터 방식을 활용
        //폼데이터가 아니라 json형식으로 보냄
        //포스트맨에 row탭을 클릭하여 기본 json을 바탕으로 정보를 담는다. json형식도 바디에 담아 보내는것
        //멀티파트 폼데이터를 이용해 전송
        //단순 텍스트 데이터는 제이슨 형식/ 파일은 폼 데이터 형식

        //컨트롤러로 받을수도 있긴하지만...
        //클라이언트한테 받은 값은 반드시 유효성 체크를 해야함 왜? 보안상
        //유효성 검사를 컨트롤러 부분에 작성하면 난잡해짐
        //필터링 다 파라미터로 받아야됨
        //일반적으로 DTO로 작성함

    }
    @GetMapping("/segment/{id}/{name}")
    public String segment(@ModelAttribute QueryRequestDTO queryRequestDTO){
        return String.format("%d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }
    //메서드임
    //중괄호 있으면 세그먼트 파라미터로 인식 /{} 개수 조절 가능
    //세그먼트  파라미터를 이용한 path와 겹치지 않도록 주의

    //폼데이터 방식

    @PostMapping("/form")
        public String form(@ModelAttribute MultipartFile file){
            return String.format("%s : %s", file.getContentType(), file.getOriginalFilename());
        }

    @GetMapping("/json")
    public String json(@RequestBody QueryRequestDTO queryRequestDTO){
        return String.format("json = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }

}

//쿼리 파라미터 방식
// -어노테이션 x/ DTO객체 생성

//Segment Parameter 방식
// - @PathVariable, 각 키에 맞는 파라미터를 별도로 맵핑 메소드의 파라미터로 생성
// -@ModelAttribute, DTO객체 생성

//Form Data방식
//-@ModelAttribute, DTO객체 또는 컨트롤러 파라미터

//JSON방식
// - @RequestBody , DTO객체
