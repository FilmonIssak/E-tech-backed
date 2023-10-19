package com.Etech.Service;

import com.Etech.Dto.request.LoginRequest;
import com.Etech.Dto.request.RefreshTokenRequest;
import com.Etech.Dto.request.SignupRequest;
import com.Etech.Dto.response.LoginResponse;
import org.apache.catalina.User;

import java.io.IOException;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    User signup(SignupRequest signupRequest) throws IOException;
}
