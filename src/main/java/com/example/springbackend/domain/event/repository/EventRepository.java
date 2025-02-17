package com.example.springbackend.domain.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbackend.domain.event.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
