package com.springboot.blog.payload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "Login model information")
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
