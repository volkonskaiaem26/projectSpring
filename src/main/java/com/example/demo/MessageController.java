package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@Controller
@RestController
public class MessageController {
    private final List<String> messages = new ArrayList<>();

    //curl -X GET localhost:8080/messages -H "Content-Type: application/json"  -d "text"
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages(@RequestBody String text) {
        List<String> list = new ArrayList<>(messages);
        list.removeIf(message -> !message.contains(text));
        return ResponseEntity.ok(list);
    }

    //curl -X POST localhost:8080/messages -H "Content-Type: application/json"  -d "text"
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET localhost:8081/messages/{index}
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(messages.get(index));
    }

    //curl -X DELETE localhost:8081/messages/{index}
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl -X PUT localhost:8080/messages/{index} -H "Content-Type: application/json"  -d "text"
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET localhost:8080/messages/{text}
    @GetMapping("messages/search/{text}")
    public ResponseEntity<String> getMessage(@PathVariable("text") String text){
        for (String message : messages) {
            if (message.contains(text)) {
                return ResponseEntity.ok(message);
            }
        }
        return null;
    }

    //curl -X GET localhost:8080/messages/count
    @GetMapping("messages/count")
    public ResponseEntity<Integer> getCount(){
        return ResponseEntity.ok(messages.size());
    }

    //curl -X POST localhost:8080/messages/{index}/create -H "Content-Type: application/json"  -d "text"
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> createMessage(@PathVariable("index") Integer index, @RequestBody String message){
        messages.add(index, message);
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE localhost:8080/messages/search/{text}
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("text") String text){
        messages.removeIf(message -> message.contains(text));
        return ResponseEntity.noContent().build();
    }

}