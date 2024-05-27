package com.DailyPe.DailypeBe.repo;

import com.DailyPe.DailypeBe.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepo extends JpaRepository<Manager, Integer> {

    @Query(value = "SELECT * FROM manager WHERE manageruuid = ?1", nativeQuery = true)
    Manager findByManagerUuid(String managerUuid);
}
