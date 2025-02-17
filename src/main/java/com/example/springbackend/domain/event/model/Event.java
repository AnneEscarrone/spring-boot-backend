package com.example.springbackend.domain.event.model;

import java.util.Date;

import com.example.springbackend.domain.institution.model.Institution;
import com.example.springbackend.domain.institution.view.InstitutionView;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Event {

	@JsonView(InstitutionView.listInstitutionResume.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonView(InstitutionView.listInstitutionResume.class)
	@Column(nullable = false)
	private String name;
	
	@JsonView(InstitutionView.listInstitutionResume.class)
	@Temporal(TemporalType.DATE)
	@Column(nullable = false) 
	private Date startDate;
	
	@JsonView(InstitutionView.listInstitutionResume.class)
	@Temporal(TemporalType.DATE)
	@Column(nullable = false) 
	private Date endDate;
	
	@JsonView(InstitutionView.listInstitutionResume.class)
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name = "institution_id", nullable = false)
	private Institution institution;
	
	
	

}
