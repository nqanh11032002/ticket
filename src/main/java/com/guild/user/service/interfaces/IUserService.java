package com.guild.user.service.interfaces;

import com.guild.user.entity.User;
import com.guild.user.response.ResponseObject;
import com.nimbusds.jose.shaded.gson.JsonObject;

import java.util.Map;

public interface IUserService {
    public ResponseObject getAllUser();
    public ResponseObject getUser(String username);
    public ResponseObject updateUser(String username, User user);
    public ResponseObject insertUser(User user);
    public ResponseObject deleteUser(String username);
    public ResponseObject changePassword(Map<String, Object> inputData);
}
