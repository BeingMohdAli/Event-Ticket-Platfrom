package com.master.tickets.mappers;

import com.master.tickets.domain.CreateEventRequesting;
import com.master.tickets.domain.CreateTicketTypeRequest;
import com.master.tickets.domain.dtos.CreateEventRequestDto;
import com.master.tickets.domain.dtos.CreateEventResponseDto;
import com.master.tickets.domain.dtos.CreateTicketTypeRequestTypeDto;
import com.master.tickets.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestTypeDto dto);

    CreateEventRequesting fromDto(CreateEventRequestDto dto);


    CreateEventResponseDto toDto(Event event);

}
