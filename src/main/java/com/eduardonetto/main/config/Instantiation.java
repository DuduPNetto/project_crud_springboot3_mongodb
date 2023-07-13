package com.eduardonetto.main.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.eduardonetto.main.dto.AuthorDTO;
import com.eduardonetto.main.dto.CommentDTO;
import com.eduardonetto.main.entities.Post;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.repositories.PostRepository;
import com.eduardonetto.main.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, Instant.now(), "Let's go travel", "Travel to New York on Saturday.",
				new AuthorDTO(maria));
		Post post2 = new Post(null, Instant.now(), "Good morning", "I'm happy today!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Good travel", Instant.now(), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Have a good travel", Instant.now(), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Have a good day", Instant.now(), new AuthorDTO(alex));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));

		userRepository.save(maria);

	}

}
