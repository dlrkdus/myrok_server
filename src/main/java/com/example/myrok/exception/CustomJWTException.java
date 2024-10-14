package com.example.myrok.exception;

import com.example.myrok.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

@Getter
public class CustomJWTException extends RuntimeException implements Supplier<CustomException> {
    private ErrorCode errorCode;
    private HttpStatus httpStatus;

    public CustomJWTException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomJWTException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    @Override
    public CustomException get() {
        return new CustomException(errorCode, httpStatus);
    }
}