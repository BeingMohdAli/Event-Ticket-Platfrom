package com.master.tickets.services;

import com.master.tickets.domain.CreateEventRequesting;
import com.master.tickets.domain.entities.Event;
import com.master.tickets.domain.entities.TicketType;
import com.master.tickets.domain.entities.User;
import com.master.tickets.exceptions.UserNotFoundException;
import com.master.tickets.repositories.EventRepository;
import com.master.tickets.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
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

        List<TicketType> ticketTypes = event.getTicketTypes().stream().map(
                ticketType -> {
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    return ticketTypeToCreate;
                }).toList();


        Event eventToCreate = new Event();
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


}
