package com.negotium.negotiumapp.controller;

import com.negotium.negotiumapp.model.user.UserDto;
import com.negotium.negotiumapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static com.negotium.negotiumapp.security.v2.SecurityConstants.USER_URL;

@RestController
@RequestMapping(USER_URL)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addWithDefaultRole(@RequestBody UserDto user, Model model) {
        boolean isAdded = userService.addWithDefaultRole(user);
        if (!isAdded) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User registration has failed");
        } else {
            model.addAttribute("message", "User " + user.getUsername() + " added");
        }
        return ResponseEntity.ok(user);
    }
}