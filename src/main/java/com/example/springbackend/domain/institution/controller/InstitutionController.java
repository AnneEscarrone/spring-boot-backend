package com.example.springbackend.domain.institution.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbackend.configuration.methods.DefaultController;
import com.example.springbackend.domain.institution.model.Institution;
import com.example.springbackend.domain.institution.view.InstitutionView;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("api/institution")
public class InstitutionController extends DefaultController<Institution, Long>{

	
	@GetMapping("/find-all")
	@JsonView(InstitutionView.listInstitutionResume.class)
    public ResponseEntity<List<Institution>> findAll() {
        List<Institution> institution = service.findAll();
        return new ResponseEntity<>(institution, HttpStatus.OK);
    }
	
	@GetMapping("/find-by-id/{id}")
	@JsonView(InstitutionView.listInstitutionResume.class)
    public ResponseEntity<Institution> findById(@PathVariable Long id) {
        Optional<Institution> entity = service.findById(id);
        return entity.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
