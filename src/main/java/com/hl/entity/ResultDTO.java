package com.hl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


public class ResultDTO {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDTO() {
        super();
    }
}
