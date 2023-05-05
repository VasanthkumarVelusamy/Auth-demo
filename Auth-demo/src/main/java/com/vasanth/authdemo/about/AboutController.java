package com.vasanth.authdemo.about;

import com.vasanth.authdemo.user.UserResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/about")
public class AboutController {
    @GetMapping("")
    String about() {
        return "This is a demo app for Auth";
    }

    @GetMapping("/me")
    String aboutMe() {
        return "This is about me";
    }

    @GetMapping("/private")
    String privateAbout(@AuthenticationPrincipal UserResponseDto user) {
        String username = user.getUsername();
        return "This is private about information. You are logged in as " + username;
    }
}
