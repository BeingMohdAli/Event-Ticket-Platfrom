package com.master.tickets.services;

import com.master.tickets.domain.CreateEventRequesting;
import com.master.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {


    Event createEvent(UUID organiserId, CreateEventRequesting event);


}