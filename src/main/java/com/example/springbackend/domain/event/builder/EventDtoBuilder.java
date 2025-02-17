package com.example.springbackend.domain.event.builder;

import com.example.springbackend.domain.event.dto.EventDto;
import com.example.springbackend.domain.event.model.Event;

public class EventDtoBuilder {

	public EventDto build(Event event) {
		return EventDto.builder().id(event.getId()).startDate(event.getStartDate()).endDate(event.getEndDate())
				.active(event.isActive()).build();
	}

}
