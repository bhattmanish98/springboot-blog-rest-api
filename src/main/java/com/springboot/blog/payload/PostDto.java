package com.springboot.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Post model information")
@Data
public class PostDto {

    @ApiModelProperty(value = "Blog post id")
    private long id;

    @ApiModelProperty(value = "Blog post title")
//    title should not be null or empty and should have more than two characters
    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Post title should have two characters")
    private String title;

    @ApiModelProperty(value = "Blog post description")
//    description should not be null or empty and should have characters between 10 to 30
    @NotNull
    @NotEmpty
    @Size(min = 10, max = 30, message = "Post title should have characters between 10 to 30")
    private String description;

    @ApiModelProperty(value = "Blog post content")
//    content should not be null or empty
    @NotNull
    @NotEmpty
    private String content;

    @ApiModelProperty(value = "Blog post comments")
    private Set<CommentDto> comments;
}
