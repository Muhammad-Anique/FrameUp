package com.example.FrameUpServer.Model.Post;

import com.example.FrameUpServer.Model.Person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostDoa {
    @Autowired
    private PostRepository postRepository;
    public Post savePost(Post post)
    {
        return postRepository.save(post);
    }
}
