package com.todoapp.app.restcontroller;

import com.todoapp.app.custom_exceptions.NotFoundException;
import com.todoapp.app.entity.User;
import com.todoapp.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    private Logger logger = LoggerFactory.getLogger( this.getClass() );

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

            //logger.info( "herrreeeee" );
            throw new NotFoundException( "User Not Found" , HttpStatus.NOT_FOUND.value() );
        }

        User user = userOptional.get();
        return user;
    }

    @PostMapping(value = "/users")
    public void createUser(@Valid @RequestBody User user) {

        User savedUser = userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@Valid @RequestBody User user,@PathVariable int id)
    {
        Optional<User> userOptional = userRepository.findById(id);


        logger.info( user.toString() );

        if(!userOptional.isPresent()){

            throw new NotFoundException(  "User Not Found herrr" , HttpStatus.NOT_FOUND.value() );
        }

        user.setUid( id );
        //logger.info( "Before save" );
        userRepository.save(user);
        //logger.info( "after save" );
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){

            throw new NotFoundException(  "User Not Found herrr" , HttpStatus.NOT_FOUND.value() );
        }
        userRepository.deleteById(id);
    }

}
