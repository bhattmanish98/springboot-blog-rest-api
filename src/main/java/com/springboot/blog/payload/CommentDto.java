package com.springboot.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Comment model information")
@Data
public class CommentDto {
    @ApiModelProperty(value = "Comment id")
    private long id;
    @ApiModelProperty(value = "Comment name")
    @NotNull @NotEmpty
    @Size(min = 5, max = 30, message = "Comment name should have characters between 10 to 30")
    private String name;

    @ApiModelProperty(value = "Comment email")
    @NotNull @NotEmpty @Email
    private String email;

    @ApiModelProperty(value = "Comment body")
    @NotNull @NotEmpty
    private String body;
}
