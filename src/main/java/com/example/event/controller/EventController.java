package com.example.event.controller;

import com.example.event.common.Converter;
import com.example.event.dto.EventDto;
import com.example.event.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final Converter converter;



    @PostMapping
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventDto eventDto) {
        eventService.create(eventDto);
        return converter.buildReposeEntity(Map.of("message", "Event created successfully"), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> getEventAll() {
        return converter.buildReposeEntity(Map.of("data", eventService.findAll()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        return converter.buildReposeEntity(Map.of("data", eventService.getEventById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEventById( @PathVariable Long id,@Valid @RequestBody EventDto eventDto) {
        return converter.buildReposeEntity(Map.of("data", eventService.update(eventDto,id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEventById(@PathVariable Long id) {
        eventService.delete(id);
        return converter.buildReposeEntity(Map.of("message", "Event Deleted successfully"), HttpStatus.ACCEPTED);
    }




}