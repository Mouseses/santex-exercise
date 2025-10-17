package com.example.demo.domain.service;

import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.persistence.adapter.UserRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DemoService {

    @Autowired
    UserRepositoryAdapter userRepositoryAdapter;


    public Map<String, String> getHello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot!");
        response.put("timestamp", LocalDateTime.now().toString());
        return response;
    }

    public List<User> getUsers() {
        return new ArrayList<>(userRepositoryAdapter.findAll());
    }

    public User getUserByEmail(String email) {
        return userRepositoryAdapter.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserById(Long id) {
        return userRepositoryAdapter.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(User user) {
        return userRepositoryAdapter.save(user);
    }


    public User updateUserByEmail(String email, User user) {
        User oldUser = userRepositoryAdapter.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());

        return userRepositoryAdapter.save(oldUser);
    }

    public void deleteUser(Long id) {
        userRepositoryAdapter.delete(id);
    }
}
