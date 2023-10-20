package com.Etech.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class LoginRequest {
    private String email;
    private String password;

    @Override
    public String toString() {
        return "{\"username\":\"" + email + "\", \"password\":\"" + password + "\"}";
    }
}
