package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;

@Controller
@RestController
public class ApiController {

    private List <String> books = new ArrayList<>();

    @GetMapping("books")
    public ResponseEntity<String> getText() {
        return ResponseEntity.ok("List of books:"+String.join(" ", books));
    }

    @GetMapping("books/{name}")
    public ResponseEntity<String> createBook(@PathVariable("name") String name){
        books.add(name);
        return ResponseEntity.ok("New book:"+name);
    }
}

