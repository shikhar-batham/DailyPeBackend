package com.DailyPe.DailypeBe.controller;

import com.DailyPe.DailypeBe.payload.UserDto;
import com.DailyPe.DailypeBe.service.UserService;
import com.dailypebe.DailyPeBE.payload.DeleteUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        UserDto user = this.userService.createUser(userDto);

        if (user == null) return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> allUsers = this.userService.getAllUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/getUserByMobileNumber")
    public ResponseEntity<UserDto> getUserByMobileNumber(String mobileNumber) {

        UserDto userByMobileNumber = this.userService.getUserByMobileNumber(mobileNumber);

        return new ResponseEntity<>(userByMobileNumber, HttpStatus.FOUND);
    }


    @GetMapping("/getUserByUUID")
    public ResponseEntity<UserDto> getUserByUUID(String userUUID) {

        UserDto userByUUID = this.userService.getUserByUUID(userUUID);

        return new ResponseEntity<>(userByUUID, HttpStatus.OK);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UserDto> getUserByUserId(Integer userId) {
        UserDto userByUserId = this.userService.getUserByUserId(userId);

        return new ResponseEntity<>(userByUserId, HttpStatus.OK);
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody DeleteUserDto deleteUserDto) {

        Boolean result = this.userService.deleteUser(deleteUserDto);

        if (result) return "User was deleted successfully";

        return "Can't delete user";
    }

    @PostMapping("/updateUser/{userId}")
    public String updateUser(@RequestBody UserDto userDto, @PathVariable("userId") int userId) {

        return this.userService.updateUser(userDto, userId);
    }
}
