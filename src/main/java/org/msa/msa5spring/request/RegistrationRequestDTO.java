package org.msa.msa5spring.request;

import jakarta.validation.constraints.*;

public record RegistrationRequestDTO(
        //이메일 필수냐? ㅇ 그럼 널 안받아야댐
        @NotBlank(message = "이메일 필수") //빈문자열도 걸러냄
        @Pattern(regexp = "^[a-z]{4,8}$", message = "이메일 패턴 틀림")
        //패턴이라는 어노테이션에 리젝스라는 속성
        // @NotEmpty //널이냐 아니냐
        //자카르타(발리데이션)패키지가 유효성 패키지임
        String email,
        @NotBlank(message = "비밀번호 필수 =")
        @Size(min = 2, max = 10, message = "비밀번호는 2~10글자")
        String password,
        String name,
        @Min(value = 0, message = "나이는 최소 0살부터 입니다")
        @Max(value = 999, message = "나이는 최대 999살까지 입니다.")
        Integer age
        //왜 이메일만 보내면 통과지?
) {

}
