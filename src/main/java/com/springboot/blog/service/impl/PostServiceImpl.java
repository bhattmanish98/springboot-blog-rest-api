package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO into entity
        Post createdPost = postRepository.save(mapPostDtoToEntity(postDto));

        //Now we need to convert the Post entity to PostDto and return PostDto
        return mapEntityToPostDto(createdPost);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy) : Sort.by(sortBy).descending());
        Page<Post> allPost = postRepository.findAll(pageable);

        //get content from page object
        List<Post> listOfPost = allPost.getContent();
        List<PostDto> listOfPostDto =  listOfPost.stream().map(post -> mapEntityToPostDto(post)).collect(Collectors.toList());
        return createPostResponse(listOfPostDto, allPost.getNumber(), allPost.getSize(), allPost.getTotalPages(), allPost.getTotalElements(), allPost.isLast());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapEntityToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return mapEntityToPostDto(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    // Convert Entity into Dto
    private PostDto mapEntityToPostDto(Post post){
        //Object Mapping using model mapper
        PostDto postDto = modelMapper.map(post, PostDto.class);

        // Normal object mapping
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }

    // convert Dto to Entity
    private Post mapPostDtoToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);

//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }

    private PostResponse createPostResponse(List<PostDto> listOfPostDto, int pageNo, int pageSize, int totalPage, long totalElement, boolean isLast){
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(listOfPostDto);
        postResponse.setPageNo(pageNo);
        postResponse.setPageSize(pageSize);
        postResponse.setTotalPages(totalPage);
        postResponse.setTotalElements(totalElement);
        postResponse.setLast(isLast);
        return postResponse;
    }
}
