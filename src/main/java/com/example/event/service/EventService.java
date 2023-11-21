package com.example.event.service;

import com.example.event.dto.EventDto;

import java.util.List;


public interface EventService {

    List<EventDto> findAll();
    void create(EventDto eventDto);


    EventDto update(EventDto eventDto, Long id);

    EventDto getEventById(Long id);

    void delete(Long id);

}
