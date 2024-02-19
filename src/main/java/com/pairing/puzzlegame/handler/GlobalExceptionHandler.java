package com.pairing.puzzlegame.handler;

import com.pairing.puzzlegame.exception.InvalidGameIdException;
import com.pairing.puzzlegame.exception.InvalidGameType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidGameType.class)
    public ResponseEntity<String> invalidGameType(InvalidGameType invalidGameType) {
        return new ResponseEntity<>("Invalid Game Type", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidGameIdException.class)
    public ResponseEntity<String> invalidGameType(InvalidGameIdException invalidGameType) {
        return new ResponseEntity<>("Invalid Game Id", HttpStatus.BAD_REQUEST);
    }
}