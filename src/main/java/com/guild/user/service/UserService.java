package com.guild.user.service;

import com.guild.user.common.CusResponseMessage;
import com.guild.user.converter.UserDTOConverter;
import com.guild.user.dto.UserDTO;
import com.guild.user.entity.User;
import com.guild.user.repository.IUserRepository;
import com.guild.user.response.ResponseObject;
import com.guild.user.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private final UserDTOConverter userDTOConverter = new UserDTOConverter();

    @Override
    public ResponseObject getAllUser() {
        List<User> userList = userRepository.findAll();

        if ((long) userList.size() == 0) {

           return ResponseObject.builder()
                   .status(HttpStatus.OK.name())
                   .message(CusResponseMessage.emptyUsersMess)
                   .data(null).build();
        }

        //Convert User to UserDTO
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList)
        {
            userDTOList.add(userDTOConverter.userToUserDTO(user));
        }


        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedUsersMess)
                .data(userDTOList).build();
    }

    @Override
    public ResponseObject getUser(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);

        if (user.isEmpty()) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.notFoundUserMess)
                    .data(null).build();
        }

        //Convert User to UserDTO
        UserDTO userDTO = userDTOConverter.userToUserDTO(user.get());

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.existedUserMess)
                .data(userDTO).build();
    }

    @Override
    public ResponseObject updateUser(String username, User user) {
        Optional<User> userFound = userRepository.findUserByUsername(username);

        if(userFound.isEmpty()){
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.notFoundUserMess)
                    .data(null).build();
        }

        User userUpdate = User.builder()
                    .username((userFound.get()).getUsername())
                    .password((userFound.get()).getPassword())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .role(user.getRole()).build();
        userRepository.save(userUpdate);

        UserDTO userDTO = userDTOConverter.userToUserDTO(userUpdate);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.updateUserSuccessMess)
                .data(userDTO).build();
    }

    @Override
    public ResponseObject insertUser(User user) {
        Optional<User> userFoundByUserName = userRepository.findUserByUsername(user.getUsername());

        if(userFoundByUserName.isPresent())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.existedUsernameMess)
                    .data(null).build();
        }

        //Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        //Convert user to userDTO
        UserDTO userDTO = userDTOConverter.userToUserDTO(user);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.insertUserSuccessMess)
                .data(userDTO).build();
    }

    @Override
    public ResponseObject deleteUser(String username) {
        Optional<User> userFound = userRepository.findUserByUsername(username);

        if(userFound.isEmpty()){
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.notFoundUserMess)
                    .data(null).build();
        }
        userRepository.delete(userFound.get());

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.deleteUserSuccessMess)
                .data(null).build();
    }

    @Override
    public ResponseObject changePassword(Map<String, Object> inputData) {

        String username = inputData.get("username").toString();
        String oldPassword = inputData.get("oldPassword").toString();
        String newPassword = inputData.get("newPassword").toString();

        Optional<User> userFound = userRepository.findUserByUsername(username);

        // Check existed username
        if(userFound.isEmpty()){
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.notFoundUserMess)
                    .data(null).build();
        }

        // Check correct old password
        if (!passwordEncoder.matches(oldPassword, userFound.get().getPassword()))
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(CusResponseMessage.incorrectOldPassword)
                    .data(null).build();
        }

        User user = userFound.get();
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(CusResponseMessage.changePassSuccessMess)
                .data(null).build();
    }
}
