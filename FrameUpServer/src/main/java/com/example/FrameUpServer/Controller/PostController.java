package com.example.FrameUpServer.Controller;


import com.example.FrameUpServer.Model.Post.Post;
import com.example.FrameUpServer.Model.Post.PostDoa;
import com.example.FrameUpServer.Model.Visitor.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostDoa postDoa;

    @PostMapping("/post/save")
    public Post save(@RequestBody Post post) {
        return postDoa.savePost(post);
    }

    @GetMapping("/post/get-all")
    public List<Post> getAllPosts()
    {
        return postDoa.getAllPosts();
    }
}
