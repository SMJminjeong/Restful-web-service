package com.example.restfulwebservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//설정 관련 어노테이션
@Configuration
//Swagger 관련 어노테이션
@EnableSwagger2
public class SwaggerConfig {

    private static final Contact DEFAULT_CONTACT
            = new Contact("minjeong", "http://www.minjeong.co.kr", "sdwp0920@gmail.com");

    //API info 값
    private static final ApiInfo DEFAULT_API_INFO
            = new ApiInfo("Awesome API Title", "My User management REST API service",
                            "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
                            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

    //produce 값
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES
            = new HashSet<>(Arrays.asList("application/json", "application/xml")); //이러한 설정은 option

    @Bean
    //api 관련된 document , 반환값으로 Docket 형태
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

}
