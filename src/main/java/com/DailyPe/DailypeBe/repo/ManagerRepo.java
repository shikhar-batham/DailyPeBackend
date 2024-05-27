package com.DailyPe.DailypeBe.repo;

import com.DailyPe.DailypeBe.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepo extends JpaRepository<Manager, Integer> {
}
