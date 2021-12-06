package com.iuh.SpringBootJWT.service;

import com.iuh.SpringBootJWT.entity.Account;
import com.iuh.SpringBootJWT.enumEntity.RoleType;
import com.iuh.SpringBootJWT.repository.AccountRepository;
import com.iuh.SpringBootJWT.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    private static final Logger logger = Logger.getLogger(AccountService.class.getName());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Không tồn tại tài khoản " + username));
        return Account.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(RoleType.ROLE_USER.toString())
                .build();
    }

    public boolean signup(SignupRequest signupRequest){
        if (accountRepository.existsByUsername(signupRequest.getUsername()))
            return false;
        Account account = Account.builder()
                .username(signupRequest.getUsername())
                .password(encoder.encode(signupRequest.getPassword()))
                .roles(RoleType.ROLE_USER.toString())
                .build();
        accountRepository.save(account);
        return true;
    }

}
