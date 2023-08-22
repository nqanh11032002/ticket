package com.guild.user.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CusResponseMessage {
    String emptyUsersMess = "List of users is empty";
    String existedUsersMess = "List of users has been found";
    String existedUsernameMess = "Username has existed";
    String insertUserSuccessMess = "Insert new user successfully";
    String updateUserSuccessMess = "Update user successfully";
    String notFoundUserMess = "User doesn't exist";
    String existedUserMess = "User was founded";
    String deleteUserSuccessMess = "Delete user successfully";
    String changePassSuccessMess = "Change password successfully";

}
