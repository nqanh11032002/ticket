package com.guild.user.controller;

import com.guild.user.controller.interfaces.IUserController;
import com.guild.user.entity.User;
import com.guild.user.response.ResponseObject;
import com.guild.user.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController implements IUserController {

    @Autowired
    private IUserService userService;

    @Override
    @GetMapping("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getAllUser() {
        return userService.getAllUser();
    }

    @Override
    @GetMapping("/find")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getUser(@RequestParam("id") int id) {
        return userService.getUser(id);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject updateUser(@PathVariable("id") int id, @RequestBody User user) {

        return userService.updateUser(id, user);
    }

    @Override
    @PostMapping("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @Override
    @DeleteMapping("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject removeUser(@RequestParam("id") int id) {
        return userService.removeUser(id);
    }
}
