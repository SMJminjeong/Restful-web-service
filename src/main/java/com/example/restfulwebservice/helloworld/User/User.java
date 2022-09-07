package com.example.restfulwebservice.helloworld.User;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password","ssn"})
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class User { //Lombok사용으로 간단하게 선언만 해줌
    private Integer id;

    // 유효성 체크
    @Size(min=2, message = "Name은 2글자 이상 입력해주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요.")
    private String name;
    //과거 데이터로 지정
    @Past
    @ApiModelProperty(notes = "등록일을 입력해주세요.")
    private Date joinDate;

//    @JsonIgnore
    @ApiModelProperty(notes = "사용자 비밀번호를 입력해주세요.")
    private String password;
//    @JsonIgnore
    @ApiModelProperty(notes = "사용자 주민번호를 입력해주세요.")
    private String ssn;

}
