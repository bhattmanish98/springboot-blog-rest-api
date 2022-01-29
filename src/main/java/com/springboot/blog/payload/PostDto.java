package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    private long id;

//    title should not be null or empty and should have more than two characters
    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Post title should have two characters")
    private String title;

//    description should not be null or empty and should have characters between 10 to 30
    @NotNull
    @NotEmpty
    @Size(min = 10, max = 30, message = "Post title should have characters between 10 to 30")
    private String description;

//    content should not be null or empty
    @NotNull
    @NotEmpty
    private String content;

    private Set<CommentDto> comments;
}
