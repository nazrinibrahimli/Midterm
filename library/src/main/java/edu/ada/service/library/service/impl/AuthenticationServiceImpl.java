package edu.ada.service.library.service.impl;

import edu.ada.service.library.controller.ErrorHandler;
import edu.ada.service.library.exceptions.InvalidCredentialsException;
import edu.ada.service.library.model.entity.UserEntity;
import edu.ada.service.library.model.repository.UserRepository;
import edu.ada.service.library.model.requestAndResponse.AuthRequestDto;
import edu.ada.service.library.model.requestAndResponse.LoginDto;
import edu.ada.service.library.model.requestAndResponse.RegisterDto;
import edu.ada.service.library.service.AuthenticationService;
import edu.ada.service.library.utils.Crypt;
import edu.ada.service.library.utils.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    protected static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);
    @Autowired


    private UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginDto login(AuthRequestDto requestDto) {
        log.info("started");

        UserEntity userEntity = userRepository.findByEmail(requestDto.getEmail());

        if (userEntity == null) {
            throw new InvalidCredentialsException("User cannot be found");
        }

        String encrypted = Crypt.encrypt(requestDto.getPassword());
        if (!userEntity.getPassword().equals(encrypted)) {
            throw new InvalidCredentialsException("password is wrong!");
        }

        userEntity.setToken(Token.generateRandomToken());
        userRepository.save(userEntity);

        log.info("log ended");
        return LoginDto.builder().token(userEntity.getToken()).build();
    }

    @Override
    public RegisterDto register(AuthRequestDto requestDto) {
        log.info("reg started");

        String encrypted = Crypt.encrypt(requestDto.getPassword());
        UserEntity userEntity = UserEntity.builder().email(requestDto.getEmail()).password(encrypted).build();

        try {
            userRepository.save(userEntity);

        } catch (Exception e) {
            throw new InvalidCredentialsException("User is exists");
        }

        log.info("reg ended");
        return RegisterDto.builder().message("Success").build();
    }
}
