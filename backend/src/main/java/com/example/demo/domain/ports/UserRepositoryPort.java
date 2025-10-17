package com.example.demo.domain.ports;

import com.example.demo.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void delete(Long id);
    Optional<User> findByEmail(String email);
}
