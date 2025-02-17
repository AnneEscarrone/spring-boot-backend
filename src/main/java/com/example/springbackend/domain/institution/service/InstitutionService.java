package com.example.springbackend.domain.institution.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbackend.configuration.methods.DefaultService;
import com.example.springbackend.domain.event.model.Event;
import com.example.springbackend.domain.event.service.EventService;
import com.example.springbackend.domain.institution.model.Institution;
import com.example.springbackend.domain.institution.repository.InstitutionRepository;

@Service
public class InstitutionService implements DefaultService<Institution, Long> {

	@Autowired
	private EventService eventService;

	@Autowired
	private InstitutionRepository repository;

	@Override
	public Institution save(Institution institution) {
	    Set<Event> events = institution.getEvents();
	    institution.setEvents(null);  
	    Institution instituitionSaved = repository.save(institution);
	    
	    if(Objects.nonNull(events)) {
	    	for (Event event : events) {
		        event.setInstitution(instituitionSaved);
		    }
		   
		    List<Event> eventsSaved = eventService.saveAll(events); 
		    Set<Event> eventSet = new LinkedHashSet<>(eventsSaved);  
		    instituitionSaved.setEvents(eventSet);
		    createScheduleTaskToEvent(instituitionSaved);
	    }
	    
	    return instituitionSaved;
	}

	@Override
	public Institution update(Institution institution) {
		var institutionUpdated = repository.save(institution);
		createScheduleTaskToEvent(institutionUpdated);

		return institutionUpdated;
	}

	private void createScheduleTaskToEvent(Institution institution) {
		institution.getEvents().stream().forEach(event -> {
			eventService.scheduleTask(event);
		});
	}

	@Override
	public List<Institution> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Institution> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
