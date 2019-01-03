package com.fst.travelagency.controller;


import com.fst.travelagency.model.User;
import com.fst.travelagency.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll().stream()
                .collect(Collectors.toList());
    }


    @PostMapping("/users/{id}")
    public Optional<User> findUserById(@PathVariable long id) {

        Optional<User> user =  repository.findById(id);
        if ((user!=null)){
            return user;
        }
        return null;
    }

    @PutMapping("/users/update/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {
        Optional<User> userOptional = repository.findById(id);
        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();
        user.setId(id);
        repository.save(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        repository.deleteById(id);
    }


}