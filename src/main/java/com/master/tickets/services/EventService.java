package com.master.tickets.services;

import com.master.tickets.domain.CreateEventRequesting;
import com.master.tickets.domain.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {


    Event createEvent(UUID organiserId, CreateEventRequesting event);
    Page<Event> listEVentsForOrganiser(UUID organiserId, Pageable pageable);


}