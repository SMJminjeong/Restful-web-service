package com.example.restfulwebservice.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password","name"})
public class User { //Lombok사용으로 간단하게 선언만 해줌
    private Integer id;

    // 유효성 체크
    @Size(min=2, message = "Name은 2글자 이상 입력해주세요.")
    private String name;
    //과거 데이터로 지정
    @Past
    private Date joinDate;

//    @JsonIgnore
    private String password;
//    @JsonIgnore
    private String ssn;

}
