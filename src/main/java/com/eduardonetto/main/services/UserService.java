package com.eduardonetto.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.dto.UserDTO;
import com.eduardonetto.main.entities.User;
import com.eduardonetto.main.repositories.UserRepository;
import com.eduardonetto.main.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		if (user == null) {
			throw new ObjectNotFoundException("Object not found. Id " + id);
		}
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id " + id));
	}

	public User insert(User user) {
		return repository.insert(user);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public User update(String id, User user) {
		User newUser = findById(id);
		newUser = updateData(newUser, user);
		return repository.save(newUser);
	}

	public User updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		return newUser;
	}

	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}

}
