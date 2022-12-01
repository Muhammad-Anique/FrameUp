package com.example.FrameUpServer.Model.Post;

import com.example.FrameUpServer.Model.Person.Person;
import com.example.FrameUpServer.Model.Visitor.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostDoa {
    @Autowired
    private PostRepository postRepository;
    public Post savePost(Post post)
    {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts()
    {
        List<Post> post = new ArrayList<>();
        Streamable.of(postRepository.findAll())
                .forEach(post::add);
        return post;
    }

    public String DeletePost(int id){

        postRepository.deleteById(id);
        return "true";

    }
}
