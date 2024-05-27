package com.dailypebe.DailyPeBE.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerDto {

    private Integer id;
    private String managerUUID;
    private String name;
}
