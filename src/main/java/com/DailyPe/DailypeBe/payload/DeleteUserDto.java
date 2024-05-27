package com.dailypebe.DailyPeBE.payload;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteUserDto {

    private String uuid;
    private String mobileNumber;
}
