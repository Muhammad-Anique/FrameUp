package com.example.FrameUpServer.Controller;


import com.example.FrameUpServer.Model.Post.Post;
import com.example.FrameUpServer.Model.Post.PostDoa;
import com.example.FrameUpServer.Model.Visitor.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/post/delete/{id}")
    public String deleteThePost(@PathVariable int id){
        return postDoa.DeletePost(id);
    }
}
