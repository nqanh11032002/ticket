package com.guild.user.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CusResponseMessage {
    public static final String emptyUsersMess = "List of users is empty";
    public static final String existedUsersMess = "List of users has been found";
    public static final String existedUsernameMess = "Username has existed";
    public static final String insertUserSuccessMess = "Insert new user successfully";
    public static final String updateUserSuccessMess = "Update user successfully";
    public static final String notFoundUserMess = "User doesn't exist";
    public static final String existedUserMess = "User was founded";
    public static final String deleteUserSuccessMess = "Delete user successfully";
    public static final String changePassSuccessMess = "Change password successfully";
    public static final String incorrectOldPassword = "Old password is incorrect";

}
