package com.guild.user.service;

import com.guild.user.entity.User;
import com.guild.user.repository.IUserRepository;
import com.guild.user.response.ResponseObject;
import com.guild.user.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseObject getAllUser() {
        List<User> userList = userRepository.findAll();

        if (userList.stream().count() == 0) {
           return new ResponseObject(HttpStatus.OK.name(), "List of users is empty", userList);
        }

        return new ResponseObject(HttpStatus.OK.name(), "List of users has been found", userList);
    }

    @Override
    public ResponseObject getUser(int id) {
        var user = userRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseObject(HttpStatus.OK.name(), "Not found user by ID: " + id, user);
        }

        return new ResponseObject(HttpStatus.OK.name(), "User with ID: " + id + " found.", user);
    }

    @Override
    public ResponseObject updateUser(int id, User user) {
        var userFound = userRepository.findById(id);

        if(!userFound.isPresent()){
            return new ResponseObject(HttpStatus.OK.name(), "Not found user by ID: " + id + " to update", user);
        }

        User userUpdate = userFound.get();
        userUpdate.setUserName(user.getUserName());
        userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPhone(user.getPhone());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setRole_id(user.getRole_id());
        userRepository.save(userUpdate);

        return new ResponseObject(HttpStatus.OK.name(), "Update user by ID: " + id + " successfully", userUpdate);
    }

    @Override
    public ResponseObject insertUser(User user) {
        var userFoundByUserName = userRepository.findByUserName(user.getUserName());

        if(userFoundByUserName.isPresent())
        {
            return new ResponseObject(HttpStatus.CREATED.name(), "UserName: " + user.getUserName() + " is exists in table User", null);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return new ResponseObject(HttpStatus.CREATED.name(), "Insert new user successfully", user);
    }

    @Override
    public ResponseObject removeUser(int id) {
        var userFound = userRepository.findById(id);

        if(!userFound.isPresent()){
            return new ResponseObject(HttpStatus.OK.name(), "Not found user by ID: " + id + " to delete", null);
        }

        userRepository.deleteById(id);

        return new ResponseObject(HttpStatus.OK.name(), "Delete user by ID: " + id + " successfully", userFound);
    }
}
