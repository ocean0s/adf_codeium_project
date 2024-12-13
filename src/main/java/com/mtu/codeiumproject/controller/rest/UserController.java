package com.mtu.codeiumproject.controller.rest;

import com.mtu.codeiumproject.entity.MyUser;
import com.mtu.codeiumproject.security.dtos.NewUser;
import com.mtu.codeiumproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myapi/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<MyUser> createUser(@Valid @RequestBody NewUser user) {
        MyUser myUser = new MyUser(user.username(), user.password(), MyUser.Role.USER, true);
        MyUser createdUser = userService.createUser(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}/reset-password")
    public ResponseEntity<MyUser> resetPassword(@PathVariable Long id, @Valid @RequestBody NewUser user) {
        MyUser myUser = userService.getUserById(id);
        myUser.setPassword(user.password());
        MyUser updatedUser = userService.updateUser(myUser);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{id}/toggle-unlocked")
    public ResponseEntity<MyUser> toggleUnlocked(@PathVariable Long id) {
        MyUser myUser = userService.getUserById(id);
        myUser.setUnlocked(!myUser.isUnlocked());
        MyUser updatedUser = userService.updateUser(myUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


}

