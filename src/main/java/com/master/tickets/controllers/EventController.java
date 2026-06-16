package com.master.tickets.controllers;

import com.master.tickets.domain.CreateEventRequesting;
import com.master.tickets.domain.dtos.CreateEventRequestDto;
import com.master.tickets.domain.dtos.CreateEventResponseDto;
import com.master.tickets.domain.entities.Event;
import com.master.tickets.mappers.EventMapper;
import com.master.tickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto
    ) {
        // Extract organiser ID from JWT
        UUID organiserId = UUID.fromString(jwt.getSubject());

        // Map DTO -> CreateEventRequesting
        CreateEventRequesting createEventRequesting = eventMapper.fromDto(createEventRequestDto);

        // Call service with organiserId
        Event event = eventService.createEvent(organiserId, createEventRequesting);

        // Map Event -> Response DTO
        CreateEventResponseDto response = eventMapper.toDto(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
