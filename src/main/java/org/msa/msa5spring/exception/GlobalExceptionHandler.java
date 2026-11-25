package org.msa.msa5spring.exception;

import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    //예외가 발생하는 객체 넣어주기
    //이 소괄호 안 전달해준것에 예외가 발생하면 밑 메서드를 실행 하겠다
    //익셉션은 최상위 객체라 이 밑으로 예외가 발생하면 모두 실행이 가능
    //이 익셉션이라는 이름만 적으면 타입, .class 라는 프로퍼티까지 접근을 하면
    //익셉션이라는 인스턴스를 뜻하게 됨.
    //run time > 서버가 안멈추고 프로세스를 계속 유지하고 있는 시간
    //같으면은 해당 메서드를 실행한다.
    //익셉션이라는 객체를 생성해서 어노테이션에 전달.
    //어노테이션 문법 제약이 있어서 new로 생성은 안됨
    //내가 핸들링 하고싶은 객체 명을 적고 객체의 리터럴을 생성하는 .class라는 걸 이용해서 어노테이션에 전달
    public ResponseEntity<GlobalResponseDTO<Void>> handle(Exception e){
        return ResponseEntity
                .status(500)
                .body(
                        new GlobalResponseDTO<>(
                                "599",
                                "시스템 에러",
                                null
                        )
                                //보이드는 result에만 쓰임
        );
    }
}
