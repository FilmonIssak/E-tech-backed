package com.Etech.Dto.response;

import com.Etech.Model.enums.CustomerStatus;
import com.Etech.Model.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String firstName;
    private long id;
    private RoleType role;
    private CustomerStatus status;

    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public LoginResponse(String accessToken, String refreshToken, String firstName, long userId, RoleType role, CustomerStatus status) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.firstName = firstName;
        this.id = userId;
        this.role = role;
        this.status = status;
    }
}