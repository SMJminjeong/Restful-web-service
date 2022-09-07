package com.example.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    //어노테이션을 통한 의존성 주입
    @Autowired
    private MessageSource source;

    // GET 방식의 메서드 형태
    // 지정하고싶은 URI : hello-world (endpoint)

    //@RequestMapping()을 사용해왔지만 더 편리한 GetMapping() 사용가능
    //@RequestMapping(method=RequestMethod.GET, path="/hello-world")
    //test2
    @GetMapping(path ="/hello-world") //추가로 더 추가할 시 속성도 추가해야 함2
    public String helloWorld(){
        return "Hello World";
    }

    //alt + enter : 클래스 생성
    @GetMapping(path ="/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path ="/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){ //매개변수 지정 = 오버로딩
        return new HelloWorldBean(String.format("Hello World, %s",  name));
        //%s 문자 가변데이터를 받겠다.
        //String.format() 함수 사용
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(
                        @RequestHeader(name = "Accept-Language", required = false) Locale locale){
        //다국어 지역 설정을 header값에 포함
        //Accept-Language라는 값이 Header에 포함되지 않았을 경우에는 자동적으로 Locale값이 보이게 된다.

        return source.getMessage("greeting.message",null, locale);
    }
}
