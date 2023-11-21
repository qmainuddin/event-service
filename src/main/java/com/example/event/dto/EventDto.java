package com.example.event.dto;

import lombok.Data;
import com.example.event.common.enums.EventType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class EventDto implements Serializable {
    Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    String title;

    String description;
    @NotNull
    EventType eventType;
    @NotNull
    @NotEmpty
    @NotBlank
    String location;
    @NotNull
    LocalDate staringDate;
    @NotNull
    LocalDateTime endingDate;
}