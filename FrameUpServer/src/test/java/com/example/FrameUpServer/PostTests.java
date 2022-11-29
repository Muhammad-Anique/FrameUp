package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.Person.Person;
import com.example.FrameUpServer.Model.Post.Post;
import com.example.FrameUpServer.Model.Post.PostDoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostTests {
    @Autowired
    private PostDoa postDoa;
    @Test
    void postSaveTest(){
//        Post post=new Post();
//        post.setPostAll("Event","02/33/2022","Event","DramaF","Sadia","This is event for something", "http://", "#anique",4);
//        postDoa.savePost(post);
    }

}
