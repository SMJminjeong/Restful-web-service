package com.example.restfulwebservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//lombok : setter, getter, 생성자, toString, equals 메서드 자동 생성
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldBean {
      private String message;

//    public String getMessage(){
//        return this.message;
//    }
//    public void setMessage(){
//        this.message = msg;
//    }
//    public HelloWorldBean(String message) { //메서드를 가지고있는 생성자가 생성
//        //사용하겠다고 한 HelloWorldBean에서 String값을 포함한 채 생성했기 때문에
//        this.message = message;
//    }
}
