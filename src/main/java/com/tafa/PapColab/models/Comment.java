package com.tafa.PapColab.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;

	private String comment;

	// Constructors, getters, and setters

	public Comment() {
		// Default constructor
	}

	public Comment(String comment, Post post, User user) {
		super();
		this.comment = comment;
		this.post = post;
		this.user = user;
	}

	// Getters and setters

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

	// toString() method for debugging and logging

	@Override
	public String toString() {
		return "Post{" +
				"id=" + id +
				", comment='" + comment + '\'' +
				", post='" + post + '\'' +
				", user='" + user + '\'' +
				'}';
	}

	public Comment orElseThrow(Object object) {
		return null;
	}
}
