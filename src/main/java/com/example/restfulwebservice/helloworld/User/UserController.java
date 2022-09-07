package com.example.restfulwebservice.helloworld.User;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    //User라는 인스턴스 값을 new라는 키워드로 생성하는 것이 아닌
//    private UserDaoService service = new ;

    //의존성 주입 DI 사용
    private UserDaoService service;
    //생성자를 통한 주입
    public UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users") //endpoint : /users
    //전체 사용자 목록 반환 메서드
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //GET /users/1 or /users/10 -> String 형태로 전달
    @GetMapping("/users/{id}")
    //사용자 1명 반환 메서드
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        //HATEOAS
//      EntityModel<User> model = EntityModel.of(user,linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users"));
        //매개변수에 위에서 검색된 user변수를 지정
        EntityModel<User> entityModel = EntityModel.of(user);
        //user 반환시 추가시킬 수 있는 링크를 추가
//        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
//                                        .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkBuilder.withRel("all-users"));

        return entityModel;
    }

    //POST 웹 브라우저에서 실행 가능 : html, js, jquery 등 필요
    @PostMapping("/users")
    public ResponseEntity<User> createUser( @Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }

    @PutMapping("/users/{id}")private User updateUser(@PathVariable int id, @RequestBody User user) {
        User updateUser = service.updateUser(id, user);

        if (updateUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return updateUser;
    }

}
