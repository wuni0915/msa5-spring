package org.msa.msa5spring.exception;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
//이거 있으면 어쩌고
@RestControllerAdvice
public class GlobalExceptionHandler {
    // ----------------------------------------
    //유효성 체크 예외 관련 (이 두개로 분리해서 작업하게 됨)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    //어떤 예외를 담당 할 건지
    public ResponseEntity<GlobalResponseDTO<String>> handle(MethodArgumentTypeMismatchException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new GlobalResponseDTO<>(
                                "E01",
                                "유효성 체크 에러",
                                String.format("%s:필드를 확인 해 주세요.", e.getName())
                        )
                );
    };
    //발리데이션 체크엘러는 400에러임 보통

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // //DTO객체 처리 특징/ 모둔 데이터를 다 검색하고 그 예외들을 다 모아서 한거번에 복수의 에러메세지를 반환
    // public ResponseEntity<GlobalResponseDTO<Void>> handle (MethodArgumentNotValidException e){
    //     e.getBindingResult()
    //             .getFieldErrors()
    //             //List<FieldError>로 가져온다
    //             .stream()
    //             .collect(Collectors.toMap(
    //                     FieldError::getField, //필드명을 가져와서 Map의 Key로 사용
    //                     (fieldError) -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "유효하지 않은 값입니다.", (existing, replacement) -> existing
    //                     //기존값, 새로운값을 받아서 기존값을 그대로 리턴함. 덮어쓰지 않음 초기세팅 그대로
    //             ));
    //     //아규먼트로 전달하는 값을 맵 형태로 변환/ collect라는 메서드임
    //
    // };
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseDTO<Map<String, String>>> handle(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                //리스트를 스트림으로 바꾼다.
                .collect(Collectors.toMap( // 이메일 스트림 / 키 밑 값
                //맵으로 바꿔서 그 결과들을 모은다
                        FieldError::getField, // 필드명을 가져와서 Map의 Key로 사용
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
                );
    }

    // ----------------------------------------
    //그외 모든 예외 처리 핸들러 메소드
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
        log.trace("트레이스 로깅");
        log.debug("디버그 로깅");
        log.info("인포 로깅");
        log.warn("워닝 로깅");
        log.error("에러 로깅");
        //log = 롬복을 받아서 사용 가능한 변수
        //로깅 레벨 설정에 따라 출력값이 다름
        //이 코드가 있는 메서드를 반드시 실행을 시켜야 에러 결과가 나온다
        //로그가 많이 찍히는건 야믈 루트 시작점때문임
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
