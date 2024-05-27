package com.DailyPe.DailypeBe.service.serviceImpl;


import com.DailyPe.DailypeBe.entity.Manager;
import com.DailyPe.DailypeBe.entity.User;
import com.DailyPe.DailypeBe.exception.ResourceNotFoundException;
import com.DailyPe.DailypeBe.payload.UserDto;
import com.DailyPe.DailypeBe.repo.ManagerRepo;
import com.DailyPe.DailypeBe.repo.UserRepo;
import com.DailyPe.DailypeBe.service.UserService;
import com.DailyPe.DailypeBe.utility.Utility;
import com.dailypebe.DailyPeBE.payload.DeleteUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);

        Optional<User> userByMobileNumber = this.userRepo.findUserByMobileNumber(user.getMobileNumber());
        if (userByMobileNumber.isPresent()) return null;

        if (user.getFullName() == null || user.getFullName().equals("") || user.getFullName().isEmpty()) return null;

        boolean res = Utility.validateMobileNumber(user.getMobileNumber());
        if (!res) return null;

        boolean validPAN = Utility.isValidPAN(user.getPanNumber());
        if (!validPAN) return null;
        String validPANNumber = Utility.validateAndConvertPAN(user.getPanNumber());

        Calendar calendar = Calendar.getInstance();
        Date timeStamp = calendar.getTime();

        UUID userUuid = UUID.randomUUID();

        user.setPanNumber(validPANNumber);
        user.setCreatedAt(timeStamp.toString());
        user.setUserUUID(userUuid.toString());
        user.setIsActive(true);

        User createdUser = this.userRepo.save(user);

        return this.modelMapper.map(createdUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> allUsers = this.userRepo.findAll();

        return allUsers.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByMobileNumber(String mobileNumber) {

        User userByMobileNumber = this.userRepo.findUserByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user_mobile", 0));

        if (userByMobileNumber == null) return null;

        return this.modelMapper.map(userByMobileNumber, UserDto.class);
    }

    @Override
    public UserDto getUserByUUID(String userUUID) {

        User userByUUID = this.userRepo.findUserByUserUUID(userUUID)
                .orElseThrow(() -> new ResourceNotFoundException("user", "uuid", 0));

        return this.modelMapper.map(userByUUID, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(Integer userId) {

        User fetchedUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "user_id", userId));

        return this.modelMapper.map(fetchedUser, UserDto.class);
    }

    @Override
    public Boolean deleteUser(DeleteUserDto deleteUserDto) {

//        String deleteByUUID = null;
//        String deleteByMobile = null;

        if (deleteUserDto.getMobileNumber() == null && deleteUserDto.getUuid() == null) return false;

//        if (deleteUserDto.getMobileNumber() == null || deleteUserDto.getMobileNumber().equals("")) {
//            deleteByUUID = deleteUserDto.getUuid();
//        } else {
//            deleteByMobile = deleteUserDto.getMobileNumber();
//        }

        User user = null;

        try {
            user = this.userRepo.findUserByMobileNumber(deleteUserDto.getMobileNumber()).orElseThrow(() -> new ResourceNotFoundException("user", "mobileNumber", 0));
        } catch (ResourceNotFoundException ignored) {
        }

        try {
            user = this.userRepo.findUserByUserUUID(deleteUserDto.getUuid()).orElseThrow(() -> new ResourceNotFoundException("user", "mobileNumber", 0));
        } catch (ResourceNotFoundException ignored) {
        }

        if (user != null) {
            this.userRepo.delete(user);
            return true;
        }

        return false;

//        if (deleteByUUID != null) {
//            User user = this.userRepo.findUserByUserUUID(deleteByUUID).orElseThrow(() -> new ResourceNotFoundException("user", "uuid", 0));
//            this.userRepo.delete(user);
//            return true;
//        } else if (deleteByMobile != null) {
//            User user = this.userRepo.findUserByMobileNumber(deleteByMobile).orElseThrow(() -> new ResourceNotFoundException("user", "MobileNumber", 0));
//            this.userRepo.delete(user);
//            return true;
//        } else return false;

    }

    @Override
    public String updateUser(UserDto userDto, int userId) {

        User fetchedUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));

        if (fetchedUser.getManagerId() == null) {
            fetchedUser.setManagerId(userDto.getManagerId());
            Calendar calendar = Calendar.getInstance();
            Date timeStamp = calendar.getTime();
            fetchedUser.setUpdatedAt(timeStamp.toString());
        }

        if (!userDto.getManagerId().isEmpty()) {

            if (userDto.getFullName() == null || userDto.getFullName().equals("") || userDto.getFullName().isEmpty())
                return "Full Name must contain some value";
            fetchedUser.setFullName(userDto.getFullName());

            boolean res = Utility.validateMobileNumber(userDto.getMobileNumber());
            if (!res) return "Invalid Mobile";

            boolean validPAN = Utility.isValidPAN(userDto.getPanNumber());
            if (!validPAN) return "Invalid PAN";
            String validPANNumber = Utility.validateAndConvertPAN(userDto.getPanNumber());
            fetchedUser.setPanNumber(validPANNumber);

            Optional<User> userByMobileNumber = this.userRepo.findUserByMobileNumber(userDto.getMobileNumber());
            if (userByMobileNumber.isPresent()) return "This Mobile Number already exist.";
            fetchedUser.setMobileNumber(userDto.getMobileNumber());

            Manager byManagerUuid = this.managerRepo.findByManagerUuid(userDto.getManagerId());
            if (byManagerUuid != null)
                fetchedUser.setManagerId(userDto.getManagerId());
            else return "Can't find manager";

            Calendar calendar = Calendar.getInstance();
            Date timeStamp = calendar.getTime();
            fetchedUser.setUpdatedAt(timeStamp.toString());
        } else
            return userDto.getFullName() + " " + userDto.getPanNumber() + " " + userDto.getMobileNumber() + " " + "These Keys  can be updated on individual basis only";

        this.userRepo.save(fetchedUser);

        return "Fields Updated successfully";
    }

}
