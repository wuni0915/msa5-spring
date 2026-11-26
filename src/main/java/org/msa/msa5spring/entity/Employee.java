package org.msa.msa5spring.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

//DTO객체 만듦 이거를 엔티티라고 부른다.
@Getter
@Setter

//아까 길었던 게터세터가 위 두 어노테이션으로 해결이 된다. 레ㄱ코드는 세터가 없음
public class Employee {
    //컬럼이 곧 필드임
    private Long empId;
    //롱과 마이에스큐엘의 빅인트가 대응됨
    //언사인드는 잘 안나옴 숫자가 안맞아서?
    //프라이미티브 타입이 아니라 객체로서의 Long를 쓴다.
    private String name;
    private LocalDate birth;
    //mysql에서 가져올때 문자열러 반환하기 떄문에 문자열로도 쓸 수 있다.
    //연원일로 로컬데이트 그대로도 사용 가능
    private Character gender;
    private LocalDate hireAt;
    private LocalDate fireAt;
    private Long supId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}