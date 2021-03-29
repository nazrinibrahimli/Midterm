package edu.ada.service.library.controller;

import edu.ada.service.library.exceptions.InvalidCredentialsException;
import edu.ada.service.library.exceptions.InvalidPickupException;
import edu.ada.service.library.exceptions.NotExistsException;
import edu.ada.service.library.exceptions.UnauthorizedException;
import edu.ada.service.library.model.requestAndResponse.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletRequest;


@RestControllerAdvice
public class ErrorHandler {

protected static Logger log = LoggerFactory.getLogger(ErrorHandler.class);
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Error handle(ServletRequest request, Exception exception) {
        log.debug(request.toString());
        log.error(exception.getMessage());

        return Error.builder().message("error").build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error handle(ServletRequest request, MethodArgumentNotValidException exception) {
        log.debug(request.toString());
        log.error(exception.getMessage());
        return Error.builder().message("params are not valid").build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCredentialsException.class)
    public Error handle(ServletRequest request, InvalidCredentialsException exception) {
        log.debug(request.toString());
        log.error(exception.getMessage());

        return Error.builder().message(exception.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotExistsException.class)
    public Error handle(ServletRequest request, NotExistsException exception) {
        log.debug(request.toString());
        log.error(exception.getMessage());

        return Error.builder().message(exception.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidPickupException.class)
    public Error handle(ServletRequest request, InvalidPickupException exception) {
        log.debug(request.toString());
        log.error(exception.getMessage());

        return Error.builder().message(exception.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Error handle(ServletRequest request, UnauthorizedException exception) {
        log.debug(request.toString());
        log.error(exception.getMessage());

        return Error.builder().message(exception.getMessage()).build();
    }




}
