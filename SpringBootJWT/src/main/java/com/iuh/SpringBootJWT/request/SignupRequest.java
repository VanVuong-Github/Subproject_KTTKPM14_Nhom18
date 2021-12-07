package com.iuh.SpringBootJWT.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class SignupRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Username không được để trống")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

}
