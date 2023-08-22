package com.guild.user.controller.interfaces;

import com.guild.user.entity.User;
import com.guild.user.response.ResponseObject;

public interface IUserController {
    public ResponseObject getAllUser();
    public ResponseObject getUser(String username);
    public ResponseObject insertUser(User user);
    public ResponseObject deleteUser(String username);
    public ResponseObject updateUser(String username, User user);
    public ResponseObject changePassword(int id);

}
