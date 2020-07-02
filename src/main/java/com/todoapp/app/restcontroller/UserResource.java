package com.todoapp.app.restcontroller;

import com.todoapp.app.custom_exceptions.UserExceptions.UserNotFoundException;
import com.todoapp.app.entity.User;
import com.todoapp.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
GET /users
GET /users/id
DELETE /users/id
PATCH /users/id
POST /users

*/

@RestController
public class UserResource {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    //@ResponseBody
    public List<User> getAllUsers()
    {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id)
    {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){

            ///////////////////////////////////////////////////////////////////////////////
            throw new UserNotFoundException();
        }

        User user = userOptional.get();
        return user;
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {

        //////*****************************************************************************
        User savedUser = userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody User user,@PathVariable int id)
    {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){

            ///////////////////////////////////////////////////////////////////////////////
            throw new UserNotFoundException();
        }
        userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){

            ///////////////////////////////////////////////////////////////////////////////
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

}
