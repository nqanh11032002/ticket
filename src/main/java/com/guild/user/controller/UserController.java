package com.guild.user.controller;

import com.guild.user.controller.interfaces.IUserController;
import com.guild.user.entity.User;
import com.guild.user.response.ResponseObject;
import com.guild.user.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController implements IUserController {

    @Autowired
    private IUserService iUserService;

    //List all users
    @Override
    @GetMapping("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getAllUser() {
        return iUserService.getAllUser();
    }

    //Get an user
    @Override
    @GetMapping("/getUser")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getUser(@RequestParam("username") String username) {
        return iUserService.getUser(username);
    }

    //Insert an user
    @Override
    @PostMapping("/register")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject insertUser(@RequestBody User user) {
        return iUserService.insertUser(user);
    }

    //Delete an user
    @Override
    @DeleteMapping ("/deleteUser")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject deleteUser(@RequestParam("username") String username) {
        return iUserService.deleteUser(username);
    }

    @Override
    @PutMapping ("/updateUser")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject updateUser(@RequestParam("username") String username, @RequestBody User user) {
        return iUserService.updateUser(username, user);
    }

    //Update password of user
    @Override
    @PutMapping ("/changePassword")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject changePassword(@RequestBody Map<String, Object> inputData) {
        return iUserService.changePassword(inputData);
    }

}
