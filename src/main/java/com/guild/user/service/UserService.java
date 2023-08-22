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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final CusResponseMessage cusResponseMessage = new CusResponseMessage();
    private final UserDTOConverter userDTOConverter = new UserDTOConverter();

    @Override
    public ResponseObject getAllUser() {
        List<User> userList = userRepository.findAll();

        if ((long) userList.size() == 0) {

           return ResponseObject.builder()
                   .status(HttpStatus.OK.name())
                   .message(cusResponseMessage.getEmptyUsersMess())
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
                .message(cusResponseMessage.getExistedUsersMess())
                .data(userDTOList).build();
    }

    @Override
    public ResponseObject getUser(String username) {
        var user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(cusResponseMessage.getNotFoundUserMess())
                    .data(null).build();
        }

        //Convert User to UserDTO
        UserDTO userDTO = userDTOConverter.userToUserDTO((User) user.get());

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(cusResponseMessage.getExistedUserMess())
                .data(userDTO).build();
    }

    @Override
    public ResponseObject updateUser(String username, User user) {
        var userFound = userRepository.findByUsername(username);

        if(userFound.isEmpty()){
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(cusResponseMessage.getNotFoundUserMess())
                    .data(null).build();
        }

        User userUpdate = User.builder()
                    .username(((User) userFound.get()).getUsername())
                    .password(((User) userFound.get()).getPassword())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .role(user.getRole()).build();
        userRepository.save(userUpdate);

        UserDTO userDTO = userDTOConverter.userToUserDTO(userUpdate);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(cusResponseMessage.getUpdateUserSuccessMess())
                .data(userDTO).build();
    }

    @Override
    public ResponseObject insertUser(User user) {
        var userFoundByUserName = userRepository.findByUsername(user.getUsername());

        if(userFoundByUserName.isPresent())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(cusResponseMessage.getExistedUserMess())
                    .data(null).build();
        }

        //Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        //Convert user to userDTO
        UserDTO userDTO = userDTOConverter.userToUserDTO(user);

        return ResponseObject.builder()
                .status(HttpStatus.CREATED.name())
                .message(cusResponseMessage.getInsertUserSuccessMess())
                .data(userDTO).build();
    }

    @Override
    public ResponseObject deleteUser(String username) {
        var userFound = userRepository.findByUsername(username);

        if(userFound.isEmpty()){
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(cusResponseMessage.getNotFoundUserMess())
                    .data(null).build();
        }
        userRepository.delete((User) userFound.get());

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(cusResponseMessage.getDeleteUserSuccessMess())
                .data(null).build();
    }
}
