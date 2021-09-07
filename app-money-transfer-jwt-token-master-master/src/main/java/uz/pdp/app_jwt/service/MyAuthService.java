package uz.pdp.app_jwt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.app_jwt.payload.ApiResponse;
import uz.pdp.app_jwt.payload.LoginDto;
import uz.pdp.app_jwt.security.JwtProvider;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class MyAuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    public ApiResponse loginToSystem(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));

            String token = jwtProvider.generateToken(loginDto.getUsername());
            return new ApiResponse("Token", true, token);
        } catch (BadCredentialsException exception) {
            return new ApiResponse("Username or password invalid", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ArrayList<User> userList = new ArrayList<>(
                Arrays.asList(
                        new User("hero", passwordEncoder.encode("123"), new ArrayList<>()),
                        new User("zero", passwordEncoder.encode("456"), new ArrayList<>()),
                        new User("pero", passwordEncoder.encode("789"), new ArrayList<>())
                ));

        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user;
        }
        throw new UsernameNotFoundException("User not found");
    }
}
