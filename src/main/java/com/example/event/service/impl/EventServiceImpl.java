package com.example.event.service.impl;

import com.example.event.dto.EventDto;
import com.example.event.entity.Event;
import com.example.event.exceptions.DataAlreadyExistException;
import com.example.event.exceptions.ResourceNotFoundException;
import com.example.event.repository.EventRepository;
import com.example.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.event.common.Converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {private final EventRepository eventRepository;
    private final Converter converter;
    private final ModelMapper modelMapper;

    @Override
    public List<EventDto> findAll() {
        return eventRepository.findAll().stream()
                .map(element -> converter.convert(element, EventDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void create(EventDto eventDto) {
        Optional.ofNullable(eventDto.getId()).ifPresent(id -> {
            if (eventRepository.existsById(id)) {
                throw new DataAlreadyExistException("EventRepository with id " + id + " already exists");
            }
        });
        eventRepository.save(converter.convert(eventDto, Event.class));
    }

    @Override
    public EventDto update(EventDto eventDto, Long id) {
        return Optional.ofNullable(eventDto.getId()).map(entityId -> {
            if (!eventRepository.existsById(entityId)) {
                throw new ResourceNotFoundException("Event with id " + entityId + " not found");
            }
            return converter.convert(eventRepository.save(converter.convert(eventDto, Event.class)), EventDto.class);
        }).orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " not found"));
    }

    @Override
    public EventDto getEventById(Long id) {
        return Optional.ofNullable(id)
                .map(eventRepository::findById)
                .map(element -> converter.convert(element, EventDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " not found"));
    }

    @Override
    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event with id " + id + " not found");
        }
        eventRepository.deleteById(id);
    }
}