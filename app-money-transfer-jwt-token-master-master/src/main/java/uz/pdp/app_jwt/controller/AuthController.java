package uz.pdp.app_jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.app_jwt.payload.ApiResponse;
import uz.pdp.app_jwt.payload.LoginDto;
import uz.pdp.app_jwt.service.MyAuthService;

@RestController
@RequestMapping(value = "/api/auth/login")
public class AuthController {

    @Autowired
    MyAuthService myAuthService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto) {
        ApiResponse apiResponse = myAuthService.loginToSystem(loginDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}

