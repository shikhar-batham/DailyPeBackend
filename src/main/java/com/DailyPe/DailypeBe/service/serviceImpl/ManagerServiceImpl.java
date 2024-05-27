package com.DailyPe.DailypeBe.service.serviceImpl;


import com.DailyPe.DailypeBe.entity.Manager;
import com.DailyPe.DailypeBe.repo.ManagerRepo;
import com.dailypebe.DailyPeBE.payload.ManagerDto;
import com.dailypebe.DailyPeBE.service.ManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ManagerDto createManager(ManagerDto managerDto) {

        Manager manager = this.modelMapper.map(managerDto, Manager.class);

        UUID managerUUID = UUID.randomUUID();

        manager.setManagerUUID(managerUUID.toString());

        Manager createdManager = this.managerRepo.save(manager);

        return this.modelMapper.map(createdManager, ManagerDto.class);
    }
}
