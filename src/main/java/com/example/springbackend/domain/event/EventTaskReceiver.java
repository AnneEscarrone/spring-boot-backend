package com.example.springbackend.domain.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbackend.domain.event.dto.EventDto;

@Component
public class EventTaskReceiver {

	@Autowired
	private ScheduledTaskEvent scheduledTaskEvent;
	
	@RabbitListener(queues = "schedule_queue")
	public void processTask(EventDto eventDto) {
		System.out.println("📌 Executando tarefa evento - : " + eventDto.getId());
		scheduledTaskEvent.scheduleRecordChange(eventDto.getId(), eventDto.getStartDate(), eventDto.getEndDate());
		System.out.println("✅ Tarefa concluída evento -: " + eventDto.getId());
	}
}
