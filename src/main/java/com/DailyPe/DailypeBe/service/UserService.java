package com.DailyPe.DailypeBe.service;

import com.DailyPe.DailypeBe.payload.UserDto;
import com.dailypebe.DailyPeBE.payload.DeleteUserDto;


import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto>getAllUsers();

    UserDto getUserByMobileNumber(String mobileNumber);

    UserDto getUserByUUID(String userUUID);

    UserDto getUserByUserId(Integer userId);

    Boolean deleteUser(DeleteUserDto deleteUserDto);
}
