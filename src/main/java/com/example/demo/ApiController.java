package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("books/{name}")
    public ResponseEntity<String> createBook(@PathVariable("name") String name){
        books.add(name);
        return ResponseEntity.ok("New book:"+name);
    }

    @DeleteMapping("books/{name}")
    public ResponseEntity<String> deleteBook(@PathVariable("name") String name){
        books.remove(name);
        return ResponseEntity.ok("Deleted book:" + name );
    }

    @PutMapping("books/{name}/{NewName}")
    public ResponseEntity<String> putBook(@PathVariable("name") String name,
                                          @PathVariable("NewName") String NewName){
        books.remove(name);
        books.add(NewName);
        return ResponseEntity.ok("Previous book: "+name+" New book: "+NewName);
    }
}

