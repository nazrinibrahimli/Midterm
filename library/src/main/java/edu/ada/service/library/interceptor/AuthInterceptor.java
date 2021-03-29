package edu.ada.service.library.interceptor;

import edu.ada.service.library.exceptions.UnauthorizedException;
import edu.ada.service.library.model.entity.UserEntity;
import edu.ada.service.library.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    private UserRepository userRepository;
    @Autowired

//    public AuthInterceptor(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");


        if (token == null) {

            throw new UnauthorizedException("unauthorized");
        }

        UserEntity userEntity = userRepository.findByToken(token);
        if (userEntity == null) {
            throw new UnauthorizedException("Unauthorized");
        }

        request.setAttribute("user", userEntity);

        return true;
    }
}
