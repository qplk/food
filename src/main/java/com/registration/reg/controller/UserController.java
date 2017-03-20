package com.registration.reg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.registration.reg.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.registration.reg.model.User;

/**
 * Created by Stasia on 15.03.17.
 */
@Controller("UserController")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity findAllUsers() {
        Iterable<User> userList = userService.findAll();
        ResponseEntity responseEntity = new ResponseEntity<>(userList, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity setUser(@PathVariable("id") Long id, @RequestBody User user) {
        User oldUser = userService.get(id);

        //update

        oldUser.setEmail(user.getEmail());
        oldUser.setGender(user.getGender());
        oldUser.setInformation(user.getInformation());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setUsername(user.getUsername());

        userService.save(oldUser);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PATCH)
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.updateUser(id, user);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
