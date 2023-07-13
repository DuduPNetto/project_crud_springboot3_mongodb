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

import com.eduardonetto.main.dto.PostDTO;
import com.eduardonetto.main.entities.Post;
import com.eduardonetto.main.services.PostService;

@RestController
@RequestMapping(value = "/posts/")
public class PostResource {

	@Autowired
	private PostService service;

	@GetMapping
	public ResponseEntity<List<PostDTO>> findAll() {
		List<Post> list = service.findAll();
		List<PostDTO> listDto = list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id) {
		Post post = service.findById(id);
		return ResponseEntity.ok().body(new PostDTO(post));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody PostDTO postDto) {
		Post entity = service.fromDTO(postDto);
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
	public ResponseEntity<PostDTO> update(@RequestBody PostDTO postDto, @PathVariable String id) {
		Post entity = service.fromDTO(postDto);
		entity = service.update(id, entity);
		return ResponseEntity.ok().body(new PostDTO(entity));
	}

}
