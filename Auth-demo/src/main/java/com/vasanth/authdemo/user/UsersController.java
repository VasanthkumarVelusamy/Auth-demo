package com.vasanth.authdemo.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UsersController {
    UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserDto createUser) {
        UserResponseDto createdUser = usersService.createUser(createUser);
        return ResponseEntity.created(URI.create("user/" + createdUser.getId())).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> verifyUser(@RequestBody LoginUserDto loginUser) {
        UserResponseDto verifiedUser = usersService.verifyUser(loginUser);
        return ResponseEntity.ok(verifiedUser);
    }

}
