package com.DailyPe.DailypeBe.payload;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer userId;
    private String userUUID;
    private String fullName;
    private String mobileNumber;
    private String panNumber;
    private String createdAt;
    private String updatedAt;
    private Boolean isActive;
    private String managerId;
}
