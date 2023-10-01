package com.tafa.PapColab.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	private String title;
	private String description;

	// Constructors, getters, and setters

	public Post() {
		// Default constructor
	}

	public Post(String title, String description, User user) {
		super();
		this.title = title;
		this.description = description;
		this.user = user;
	}

	// Getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", user='" + user + '\'' +
				'}';
	}

	public Post orElseThrow(Object object) {
		return null;
	}
}
