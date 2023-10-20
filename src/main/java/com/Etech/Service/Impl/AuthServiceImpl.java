//package com.Etech.Service.Impl;
//
//import com.Etech.Dto.request.LoginRequest;
//import com.Etech.Dto.request.RefreshTokenRequest;
//import com.Etech.Dto.request.SignupRequest;
//import com.Etech.Dto.response.LoginResponse;
//import com.Etech.Model.Customer;
//import com.Etech.Model.Role;
//import com.Etech.Model.enums.RoleType;
//import com.Etech.Repository.CustomerRepo;
//import com.Etech.Util.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.catalina.User;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class AuthServiceImpl {
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//    private final JwtUtil jwtUtil;
//    private final CustomerRepo customerRepo;
//    private final PasswordEncoder passwordEncoder;
//    private final RoleRepository roleRepository;
//    private final EmailService emailService;
//
//    @Override
//    public LoginResponse login(LoginRequest loginRequest) {
//        Authentication result = null;
//        try {
//            result = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException(e.getMessage());
//        }
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());
//        final String accessToken = jwtUtil.generateToken(userDetails);
//        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
//        User user = customerRepo.findByEmail(loginRequest.getEmail());
//        var loginResponse = new LoginResponse(accessToken, refreshToken, user.getFirstName(), user.getId(), user.getRole().getRole(), user.getStatus());
//        return loginResponse;
//    }
//
//    @Override
//    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
//        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
//        if (isRefreshTokenValid) {
//            // (check the expiration of the accessToken when request sent, if the is recent according to
//            //  issue Date, then accept the renewal)
//            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
//            if(isAccessTokenExpired)
//                System.out.println("ACCESS TOKEN IS EXPIRED"); //  Renew is this case
//            else
//                System.out.println("ACCESS TOKEN IS NOT EXPIRED");
//            final String accessToken = jwtUtil.doGenerateToken(  jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
//            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
//            // (OPTIONAL) When to renew the refresh token?
//            return loginResponse;
//        }
//        return new LoginResponse();
//    }
//
//    @Override
//    public User signup(SignupRequest signupRequest) throws IOException {
//        String firstName=  signupRequest.getFirstName();
//        String lastName = signupRequest.getLastName();
//        String email = signupRequest.getEmail();
//        String password = passwordEncoder.encode(signupRequest.getPassword());
//        String roleTypeString = signupRequest.getRole();
//        RoleType roleTypeEnum= RoleType.valueOf(roleTypeString);
//        User user;
//
//        Role role = roleRepository.findByRole(roleTypeEnum);
//
//        if(roleTypeString.equals("OWNER")) {
//            user = new Owner(firstName, lastName, email, password, UserStatus.PENDING);
//        }else {
//            user = new Customer(firstName, lastName, email, password, UserStatus.ACTIVE);
//        }
//        user.setRole(role);
//        emailService.sendWelcomeEmail(email);
//        return customerRepo.save(user);
//    }
//}
