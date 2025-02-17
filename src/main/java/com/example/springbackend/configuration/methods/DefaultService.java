package com.example.springbackend.configuration.methods;

import java.util.List;
import java.util.Optional;

public interface DefaultService<T, ID> {

	T save(T entity);
	
	T update(T entity);

	List<T> findAll();

	Optional<T> findById(ID id);

	void deleteById(ID id);
	
}
