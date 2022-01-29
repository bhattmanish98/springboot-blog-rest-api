package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;
    @NotNull @NotEmpty
    @Size(min = 5, max = 30, message = "Comment name should have characters between 10 to 30")
    private String name;

    @NotNull @NotEmpty @Email
    private String email;

    @NotNull @NotEmpty
    private String body;
}
