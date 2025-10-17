package com.example.demo.application.controller;

import com.example.demo.domain.model.User;
import com.example.demo.domain.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    DemoService demoService;

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return demoService.getHello();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(demoService.getUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserByEmail(@RequestParam(required = false) String email ,
                                               @RequestParam(required = false) Long id) {
        if(null!= email){
            return ResponseEntity.ok(demoService.getUserByEmail(email));
        }else if(null != id){
            return ResponseEntity.ok(demoService.getUserById(id));
        }
        return ResponseEntity.status(404).body("No se ingreso email o id");
    }


    @PostMapping("/user")
    public ResponseEntity<User> getUsers(@RequestBody User user) {
        return ResponseEntity.ok(demoService.createUser(user));
    }

    @PutMapping("/user")
    public ResponseEntity<User> getUsers(@RequestParam String email, @RequestBody User user) {
        return ResponseEntity.ok(demoService.updateUserByEmail(email,user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        demoService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
