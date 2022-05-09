package com.blog.services;

import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostDto getPostById(Integer postId);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> getPostsByUser(Integer userId);
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    List<PostDto> searchPosts(String keyword);

}
