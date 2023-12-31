package com.eduardonetto.main.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eduardonetto.main.dto.UserDTO;
import com.eduardonetto.main.entities.Post;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.services.UserService;

@RestController
@RequestMapping(value = "/users/")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDto) {
		User entity = service.fromDTO(userDto);
		entity = service.insert(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDto, @PathVariable String id) {
		User entity = service.fromDTO(userDto);
		entity = service.update(id, entity);
		return ResponseEntity.ok().body(new UserDTO(entity));
	}

	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findAllPosts(@PathVariable String id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}

}
