package com.example.springbackend.domain.event;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.example.springbackend.domain.event.model.Event;
import com.example.springbackend.domain.event.service.EventService;

@Service
public class ScheduledTaskEvent {

	private final ThreadPoolTaskScheduler scheduler;
	private final EventService eventService;

	@Autowired
	public ScheduledTaskEvent(ThreadPoolTaskScheduler scheduler, EventService eventService) {
		this.scheduler = scheduler;
		this.eventService = eventService;
	}

	public void scheduleRecordChange(Long eventId, Date startDate, Date endDate) {
		Instant startInstant = startDate.toInstant();
		Instant endInstant = endDate.toInstant();

		Runnable activeEvent = () -> changeEvent(true, eventId);
		scheduler.schedule(activeEvent, startInstant);

		Runnable inactiveEvent = () -> changeEvent(false, eventId);
		scheduler.schedule(inactiveEvent, endInstant);
	}

	private void changeEvent(boolean active, Long eventId) {
		Event event = eventService.findById(eventId).orElseThrow(null);
		if(Objects.nonNull(event)) {
			event.setActive(active);
			eventService.save(event);
			System.out.println("🛠 Registro alterado para "+ active +" para a event ID: " + eventId);
		}
		
	}

}
