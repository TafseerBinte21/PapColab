package com.tafa.PapColab.models;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "Email"))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	private int phone;

	private String email;

	private String password;

	private String address;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles1", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

	private Collection<Role> roles;

	@ManyToMany
	@JoinTable(name = "collaborations", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "collaboratorId"))
	private Set<User> collaborations = new HashSet<User>();

	@ManyToMany
	@JoinTable(name = "collaborationRequests", joinColumns = @JoinColumn(name = "requesterId"), inverseJoinColumns = @JoinColumn(name = "userId"))
	private Set<User> sentRequests = new HashSet<User>();

	@ManyToMany(targetEntity = User.class, mappedBy = "sentRequests")
	private Set<User> receivedRequests = new HashSet<User>();

	private String interest;

	public User() {

	}

	public User(String name, int phone, String email, String password, String address,
			Collection<Role> roles, String interest) {
		super();

		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.address = address;
		this.roles = roles;
		this.interest = interest;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Set<User> getCollaborations() {
		return collaborations;
	}

	public Set<Long> getCollaboratorIds() {
		Set<Long> collaboratorIds = new HashSet<>();
		for (User collaboration : collaborations) {
			collaboratorIds.add(collaboration.id);
		}
		return collaboratorIds;
	}

	public void setCollaborations(Set<User> collaborations) {
		this.collaborations = collaborations;
	}

	public Set<User> getSentRequests() {
		return sentRequests;
	}

	public Set<Long> getSentRequestIds() {
		Set<Long> sentRequestIds = new HashSet<>();
		for (User sentRequest : sentRequests) {
			sentRequestIds.add(sentRequest.id);
		}
		return sentRequestIds;
	}

	public void setSentRequests(Set<User> sentRequests) {
		this.sentRequests = sentRequests;
	}

	public Set<User> getReceivedRequests() {
		return receivedRequests;
	}

	public Set<Long> getReceivedRequestIds() {
		Set<Long> receivedRequestIds = new HashSet<>();
		for (User receivedRequest : receivedRequests) {
			receivedRequestIds.add(receivedRequest.id);
		}
		return receivedRequestIds;
	}

	public void setReceivedRequests(Set<User> receivedRequests) {
		this.receivedRequests = receivedRequests;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	

}
