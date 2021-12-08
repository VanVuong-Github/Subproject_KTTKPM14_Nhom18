package com.iuh.SpringBootJWT.controller;

import com.iuh.SpringBootJWT.entity.Account;
import com.iuh.SpringBootJWT.enumEntity.RoleType;
import com.iuh.SpringBootJWT.jwt.JwtUtils;
import com.iuh.SpringBootJWT.repository.AccountRepository;
import com.iuh.SpringBootJWT.request.LoginRequest;
import com.iuh.SpringBootJWT.request.SignupRequest;
import com.iuh.SpringBootJWT.response.JwtResponse;
import com.iuh.SpringBootJWT.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/{id}")
    public Account getAccountByUsername(@PathVariable("id") String username){
        return accountRepository.findByUsername(username).get();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signUpRequest) {
        // nếu tài khoản tồn tại
        if (accountRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Tài khoản đã tồn tại!"));
        }

        // đăng ký
        Account account = new Account();
        account.setUsername(signUpRequest.getUsername());
        account.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        account.setRoles(RoleType.ROLE_USER.toString());
        accountRepository.save(account);

        return ResponseEntity.ok(new MessageResponse("Đăng ký thành công!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Đăng nhập thất bại"));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account = (Account) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt, account));
    }

}
