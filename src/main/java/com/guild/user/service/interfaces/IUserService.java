package com.guild.user.service.interfaces;

import com.guild.user.entity.User;
import com.guild.user.response.ResponseObject;

public interface IUserService {
    public ResponseObject getAllUser();
    public ResponseObject getUser(int id);
    public ResponseObject updateUser(int id, User user);
    public ResponseObject insertUser(User user);
    public ResponseObject removeUser(int id);
}
