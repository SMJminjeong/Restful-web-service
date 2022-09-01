package com.example.restfulwebservice.User;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//@Component
@Service
// 도메인 정보를 이용해서 사용자 전체 목록 조회, 추가, 상세보기와 같은 비즈니스 로직 추가 예정
// 비즈니스 로직은 Service에 구현
public class UserDaoService {
    //관계형 데이터 베이스는 JPA시간에 하도록 함
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1,"Kenneth", new Date()));
        users.add(new User(2,"Alice", new Date()));
        users.add(new User(3,"Elena", new Date()));
    }

    //전체 데이터 조회
    public List<User> findAll(){
        return users;
    }

    //사용자 등록
    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount); //덧셈으로 usersCount 추가
        }
        users.add(user);
        return user;
    }

    //개별 데이터 조회
    public User findOne(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null; //데이터를 검색하지 못했을 때 null값 반환
    }

    //삭제기능 : 개별적 데이터 찾아서 삭제시켜야 함
    public User deleteById(int id){ //id 매개변수 -> 검색
        //데이터는 List에 저장해두고 겁색 가능
        //iterator : 열거형 데이터, 배열, List 형태의 데이터를 순차적으로 접근하기 위한 데이터
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()){ //순차적으로 가져옴
            User user = iterator.next(); //초기상태의 3명의 데이터가 순차적으로 포함

            if(user.getId() == id){
                iterator.remove(); //remove함수 이용해서 삭제
                return user;
            }
        }

        return null;
    }


    public User updateUser(int id, User user) {
        User updateUser = findOne(id);

        if (findOne(id) != null) {
            updateUser.setName(user.getName());
            updateUser.setJoinDate(new Date());
            return updateUser;
        }
        return null;
    }

}
