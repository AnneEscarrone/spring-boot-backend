package com.example.springbackend.domain.event.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Date startDate;
	
	private Date endDate;
	
	private boolean active;

}
