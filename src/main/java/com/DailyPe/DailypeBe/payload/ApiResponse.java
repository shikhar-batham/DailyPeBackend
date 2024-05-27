package com.DailyPe.DailypeBe.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    String message;
    Boolean success;
}
