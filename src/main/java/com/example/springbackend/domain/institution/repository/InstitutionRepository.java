package com.example.springbackend.domain.institution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbackend.domain.institution.model.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
