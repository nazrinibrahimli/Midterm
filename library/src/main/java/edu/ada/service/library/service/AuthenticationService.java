package edu.ada.service.library.service;

import edu.ada.service.library.model.requestAndResponse.AuthRequestDto;
import edu.ada.service.library.model.requestAndResponse.LoginDto;
import edu.ada.service.library.model.requestAndResponse.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    RegisterDto register(AuthRequestDto requestDto);

    LoginDto login(AuthRequestDto requestDto);
}
