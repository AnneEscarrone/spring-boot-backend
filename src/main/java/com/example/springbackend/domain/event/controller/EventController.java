package com.example.springbackend.domain.event.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbackend.configuration.methods.DefaultController;
import com.example.springbackend.domain.event.model.Event;

@RestController
@RequestMapping("api/event")
public class EventController extends DefaultController<Event, Long>{


}
