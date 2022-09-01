package com.example.restfulwebservice.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        return user;
    }

    //POST 웹 브라우저에서 실행 가능 : html, js, jquery 등 필요
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id")
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
