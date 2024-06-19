package com.example.myrok.dto.error;

import com.example.myrok.type.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String code;
    private int statusCode;
    private String detail;

    public ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.statusCode = code.getStatus();
        this.code = code.getCode();
        this.detail = code.getDetail();
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }
}
