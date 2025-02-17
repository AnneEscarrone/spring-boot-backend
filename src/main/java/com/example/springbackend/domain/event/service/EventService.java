package com.example.springbackend.domain.event.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbackend.configuration.methods.DefaultService;
import com.example.springbackend.domain.event.builder.EventDtoBuilder;
import com.example.springbackend.domain.event.dto.EventDto;
import com.example.springbackend.domain.event.model.Event;
import com.example.springbackend.domain.event.repository.EventRepository;

@Service
public class EventService implements DefaultService<Event, Long> {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private EventRepository repository;
	
	public void scheduleTask(Event event) {
		rabbitTemplate.convertAndSend("schedule_queue", new EventDtoBuilder().build(event));
	}
	
	public List<Event> saveAll(Set<Event> events) {
		return repository.saveAll(events);
	}

	@Override
	public Event save(Event event) {
		return repository.save(event);
	}

	@Override
	public List<Event> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Event> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);

	}

	@Override
	public Event update(Event entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
