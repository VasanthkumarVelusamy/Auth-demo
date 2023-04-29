package com.vasanth.authdemo.about;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("about")
public class AboutController {
    @GetMapping("")
    String about() {
        return "This is a demo app for Auth";
    }

    @GetMapping("me")
    String aboutMe() {
        return "This is about me";
    }
}
