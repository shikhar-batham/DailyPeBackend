package com.DailyPe.DailypeBe.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userUUID;

    @Column(nullable = false)
    private String fullName;
    private String mobileNumber;
    private String panNumber;
    private String createdAt;
    private String updatedAt;
    private Boolean isActive;
    private String ManagerId;
}
