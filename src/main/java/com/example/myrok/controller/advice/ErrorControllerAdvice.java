package com.example.myrok.controller.advice;

import com.example.myrok.dto.error.ErrorResponse;
import com.example.myrok.exception.CustomException;
import com.example.myrok.type.ErrorCode;
import com.example.myrok.exception.CustomJWTException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice //컨트롤러에서 발생하는 예외처리를 해당 클래스안에서 해
public class ErrorControllerAdvice {
    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.RESOURCE_NOT_FOUND);
        response.setDetail(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<ErrorResponse> customException(CustomException e) {
        ErrorResponse response = ErrorResponse.of(e.getErrorCode());
        response.setDetail(e.getMessage());
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    @ExceptionHandler({HttpClientErrorException.class, HttpMessageNotReadableException.class, NullPointerException.class, DateTimeParseException.class})
    protected ResponseEntity<ErrorResponse> httpClientErrorException(Exception e) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.INSUFFICIENT_VALID);
        response.setDetail(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_REQUEST_FORMAT);
        response.setDetail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomJWTException.class)
    protected ResponseEntity<?> handleJWTException(CustomJWTException e) {
        String msg = e.getMessage();
        return ResponseEntity.ok().body(Map.of("error", msg));
    }
}
