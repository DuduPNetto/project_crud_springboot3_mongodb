package com.eduardonetto.main.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.eduardonetto.main.entities.Post;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PostDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'")
	private Instant date;
	private String title;
	private String body;
	private AuthorDTO author;

	public PostDTO() {
	}

	public PostDTO(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.body = post.getBody();
		this.date = post.getDate();
		this.author = post.getAuthor();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.getId());
	}

}
