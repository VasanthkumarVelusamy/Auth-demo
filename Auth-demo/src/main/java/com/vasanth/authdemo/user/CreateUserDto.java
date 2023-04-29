package com.vasanth.authdemo.user;

import lombok.Data;

@Data
public class CreateUserDto {
    String username;
    String email;
    String password;
}
