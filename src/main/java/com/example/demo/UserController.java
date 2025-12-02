package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class UserController {

    private final List<User> users = new ArrayList<>();

    //curl localhost:8080/users -X POST -H "Content-Type: application/json" -d "{\"name\":\"name\", \"age\":age}
    @PostMapping("users")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.ok().build();
    }

    //curl -X GET localhost:8080/users
    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(users);
    }

    //curl -X DELETE localhost:8080/users/{index}
    @DeleteMapping("users/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable("index") Integer index) {
        users.remove((int) index);
        return ResponseEntity.noContent().build();
    }


    //curl -X GET localhost:8080/users/{index}
    @GetMapping("users/{index}")
    public ResponseEntity<User> getUser(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(users.get(index));
    }

    //curl -X PUT localhost:8080/users/{index} -H "Content-Type: application/json"  -d "age"
    @PutMapping("users/{index}")
    public ResponseEntity<Void> putAge(@PathVariable("index") Integer index, @RequestBody Integer age){
        User user = users.get(index);
        user.setAge(age);
        return ResponseEntity.accepted().build();
    }

}
