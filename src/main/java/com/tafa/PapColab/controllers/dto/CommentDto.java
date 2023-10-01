package com.tafa.PapColab.controllers.dto;

import com.tafa.PapColab.models.Post;
import com.tafa.PapColab.models.User;

public class CommentDto {

    private Long id;
    private String comment;
    private Post post;
    private User user;

    public CommentDto() {
    }

    public CommentDto(Long id, String comment, Post post, User user) {
        this.id = id;
        this.comment = comment;
        this.post = post;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}
