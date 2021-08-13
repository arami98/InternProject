package com.medialog.InternProject.network;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String message;
    private String result;

    public RegisterResponse(String message, String result) {
        this.message = message;
        this.result = result;
    }
}
