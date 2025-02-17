package com.example.springbackend.configuration.methods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class DefaultController<T, ID> {

    @Autowired
    protected DefaultService<T, ID> service;

    @PostMapping("/save")
    public ResponseEntity<T> create(@RequestBody T entity) {
        T createdEntity = service.save(entity);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }
    
    @PostMapping("/update")
    public ResponseEntity<T> update(@RequestBody T entity) {
        T createdEntity = service.update(entity);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<T>> findAll() {
        List<T> entities = service.findAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<T> findById(@PathVariable ID id) {
        Optional<T> entity = service.findById(id);
        return entity.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/remove-by-id/{id}")
    public ResponseEntity<T> remove(@PathVariable ID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


  
}
