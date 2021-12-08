package com.iuh.SpringBootJWT.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.iuh.SpringBootJWT.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonUnwrapped
    private Account account;

    private String accessToken;

    public JwtResponse(String accessToken, Account account) {
        this.accessToken = accessToken;
        this.account = account;
    }

}
