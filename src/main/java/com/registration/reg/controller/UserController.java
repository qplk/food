package com.registration.reg.controller;

import com.registration.reg.model.User;
import com.registration.reg.requestBody.UserRequestBody;
import com.registration.reg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller("UserController")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addUser(@RequestBody UserRequestBody user) {
        userService.save(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity setUser(@PathVariable("id") Long id, @RequestBody UserRequestBody user) {
       // User oldUser = userService.get(id);

        //update

        //oldUser.setEmail(user.getEmail());
        //oldUser.setGender(user.getGender());
        //oldUser.setInformation(user.getInformation());
        //oldUser.setPhoneNumber(user.getPhoneNumber());
        //oldUser.setUsername(user.getUsername());

        userService.update(user);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PATCH)
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody UserRequestBody user) {
        userService.update(user);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

