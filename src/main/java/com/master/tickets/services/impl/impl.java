package com.master.tickets.services.impl;

import com.master.tickets.domain.CreateEventRequesting;
import com.master.tickets.domain.entities.Event;
import com.master.tickets.domain.entities.TicketType;
import com.master.tickets.domain.entities.User;
import com.master.tickets.exceptions.UserNotFoundException;
import com.master.tickets.repositories.EventRepository;
import com.master.tickets.repositories.UserRepository;
import com.master.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class impl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;


    @Override
    public Event createEvent(UUID organiserId, CreateEventRequesting event) {
     User organiser = userRepository.findById(organiserId).orElseThrow(()->
             new UserNotFoundException(
                     String.format("user with ID %s not found", organiserId)
             ));
        Event eventToCreate = new Event();

        List<TicketType> ticketTypes = event.getTicketTypes().stream().map(
                ticketType -> {
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    ticketTypeToCreate.setEvent(eventToCreate);
                    return ticketTypeToCreate;
                }).toList();


     eventToCreate.setName(event.getName());
     eventToCreate.setStart(event.getStart());
     eventToCreate.setEnd(event.getEnd());
     eventToCreate.setVenue(event.getVenue());
     eventToCreate.setSalesStart(event.getSalesStart());
     eventToCreate.setSalesEnd(event.getSalesEnd());
     eventToCreate.setStatus(event.getStatus());
     eventToCreate.setOrganiser(event.getOrganiser());


        return eventRepository.save(eventToCreate);
    }

    @Override
    public Page<Event> listEVentsForOrganiser(UUID organiserId, Pageable pageable) {
        return eventRepository.findByOrganiserId(organiserId,pageable);
    }



}
