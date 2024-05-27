package com.DailyPe.DailypeBe.controller;

import com.DailyPe.DailypeBe.service.ManagerService;
import com.dailypebe.DailyPeBE.payload.ManagerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/createManager")
    public ResponseEntity<ManagerDto> createManager(@RequestBody ManagerDto managerDto) {

        ManagerDto manager = this.managerService.createManager(managerDto);

        return new ResponseEntity<>(manager, HttpStatus.CREATED);
    }

}
