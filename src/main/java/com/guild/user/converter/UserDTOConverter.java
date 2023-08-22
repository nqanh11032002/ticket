package com.guild.user.converter;

import com.guild.user.dto.UserDTO;
import com.guild.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter {

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();
    public UserDTO userToUserDTO(User user)
    {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    public User userDTOToUser(UserDTO userDTO)
    {
        User user = modelMapper.map(userDTO, User.class);

        return user;
    }
}
