package com.springboot.blog.payload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "Signup model information")
@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
