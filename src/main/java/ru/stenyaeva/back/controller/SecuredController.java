package ru.stenyaeva.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stenyaeva.back.domain.dto.UserDto;
import ru.stenyaeva.back.domain.utils.SecurityUtils;

@RestController
@RequestMapping("/secured")
@RequiredArgsConstructor
public class SecuredController {
    private final SecurityUtils securityUtils;

    @GetMapping("/ping")
    public String ping(){
        return "ping";
    }

    @GetMapping("/user/id")
    public UserDto getId(){
        return new UserDto(securityUtils.getUser());
    }


}
