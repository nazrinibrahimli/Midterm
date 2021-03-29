package edu.ada.service.library.controller;

import edu.ada.service.library.model.requestAndResponse.AuthRequestDto;
import edu.ada.service.library.model.requestAndResponse.LoginDto;
import edu.ada.service.library.model.requestAndResponse.RegisterDto;
import edu.ada.service.library.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/auth")
public class AuthenticationController {
    protected static Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value="/login")
    public LoginDto login(@Valid @RequestBody AuthRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }

    @PostMapping(value="/register")
    public RegisterDto register(@Valid @RequestBody AuthRequestDto requestDto) {
        return authenticationService.register(requestDto);
    }
}
