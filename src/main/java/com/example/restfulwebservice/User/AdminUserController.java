package com.example.restfulwebservice.User;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.logging.Filter;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service){
        this.service = service;
    }

    //전체 사용자 목록 반환 메서드
    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers(){
        List<User> users = service.findAll(); // 리스트 객체에 포함시키고 그 객체를 반환

        // service.findAll을 변수로 선언시킨 상태에서 분리하고싶다면
        //오른쪽 > Refactor > introduce Variable or Ctrl + Alt + V

        SimpleBeanPropertyFilter filter
                = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","password");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);

        mapping.setFilters(filters);

        return mapping; //mapping 값 반환
    }

    //사용자 1명 반환 메서드
    // GET /admin/users/1 -> /admin/v1/users/1
//    @GetMapping("/v1/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=1")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        SimpleBeanPropertyFilter filter
                = SimpleBeanPropertyFilter
                    .filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);

        mapping.setFilters(filters);

        return mapping;
    }

    // GET /admin/users/1 -> /admin/v2/users/1
//    @GetMapping("/v2/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=2")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        //User -> User2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); //id, name, joinDate, password, ssn
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter
                = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(userV2);

        mapping.setFilters(filters);

        return mapping;
    }


}
