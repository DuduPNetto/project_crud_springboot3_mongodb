package com.eduardonetto.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.entities.Post;
import com.eduardonetto.main.repositories.PostRepository;
import com.eduardonetto.main.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public List<Post> findAll() {
		return repository.findAll();
	}

	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id " + id));
	}

	public Post insert(Post post) {
		return repository.insert(post);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public Post update(String id, Post post) {
		Post newUser = findById(id);
		newUser = updateData(newUser, post);
		return repository.save(newUser);
	}

	public Post updateData(Post newPost, Post post) {
		newPost.setTitle(post.getTitle());
		newPost.setBody(post.getBody());
		newPost.setAuthor(post.getAuthor());
		newPost.setDate(post.getDate());
		return newPost;
	}

	public List<Post> findByTitle(String title) {
		return repository.searchTitle(title);
	}

	public List<Post> fullSearch(String text) {
		return repository.fullSearch(text);
	}

}
