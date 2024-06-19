package com.example.myrok.exception;

import com.example.myrok.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException implements Supplier<CustomException> {
  private ErrorCode errorCode;
  private HttpStatus httpStatus;

  public CustomException(ErrorCode errorCode){
    super(errorCode.getMessage());
  }

    @Override
    public CustomException get() {
        return new CustomException(errorCode, httpStatus);
    }
}
