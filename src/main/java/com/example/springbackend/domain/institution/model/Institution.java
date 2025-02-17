package com.example.springbackend.domain.institution.model;

import java.util.Set;

import com.example.springbackend.domain.event.model.Event;
import com.example.springbackend.domain.institution.view.InstitutionView;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Institution {

	@JsonView(InstitutionView.listInstitutionResume.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonView(InstitutionView.listInstitutionResume.class)
	@Column(nullable = false)
	private String name;

	@JsonView(InstitutionView.listInstitutionResume.class)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TypeInstituition type;

	@JsonView(InstitutionView.listInstitutionResume.class)
	@OneToMany(mappedBy = "institution", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Event> events;

}
