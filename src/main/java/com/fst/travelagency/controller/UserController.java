package com.fst.travelagency.controller;


import com.fst.travelagency.model.User;
import com.fst.travelagency.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // Get all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    // login
    @PostMapping("/users/{id}")
    public Optional<User> findUserById(@RequestBody User data,  @PathVariable long id) {

        Optional<User> user =  repository.findById(id);
        if ((user!=null)){
            return user;
        }
        return null;
    }


    // Update a User
    @PutMapping("/users/update/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {
        Optional<User> userOptional = repository.findById(id);
        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();
        user.setId(id);
        repository.save(user);
        return ResponseEntity.noContent().build();
    }

    //Delete a User
    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        repository.deleteById(id);
    }


}