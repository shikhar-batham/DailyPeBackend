package com.DailyPe.DailypeBe.repo;

import com.DailyPe.DailypeBe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findUserByMobileNumber(String mobileNumber);

    Optional<User>findUserByUserUUID(String userUUID);

}
