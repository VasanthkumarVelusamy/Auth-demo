package com.vasanth.authdemo.user.auth;

import com.vasanth.authdemo.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity (name = "auth-tokens")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID token;

    private Date createdDate;
    private int validityPeriod;
    @ManyToOne
    private UserEntity user;
}
