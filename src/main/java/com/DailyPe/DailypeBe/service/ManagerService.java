package com.DailyPe.DailypeBe.service;

import com.dailypebe.DailyPeBE.payload.ManagerDto;

public interface ManagerService {

    ManagerDto createManager(ManagerDto managerDto);

    ManagerDto getManagerByUuid(String uuid);
}
