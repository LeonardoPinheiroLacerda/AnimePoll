package com.leonardo.animepoll.exceptions.handler;

import javax.servlet.http.HttpServletRequest;

import com.leonardo.animepoll.exceptions.AlreadyVotedException;
import com.leonardo.animepoll.exceptions.dto.StandardError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvider {
    
    @ExceptionHandler(AlreadyVotedException.class)
    public ResponseEntity<StandardError> alreadyVoted(AlreadyVotedException exception, HttpServletRequest request){

        StandardError error = new StandardError(
            System.currentTimeMillis(), 
            HttpStatus.BAD_REQUEST.value(), 
            exception.getMessage(), 
            "Você já votou nas últimas 24 horas. Tente novamente amanhã a partir do mesmo horário que você votou anteriormente.", 
            request.getRequestURI()
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(error);
    }

}
