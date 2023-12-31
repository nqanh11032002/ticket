package com.guild.ticket.mapper;

import com.guild.ticket.dto.TicketDTO;
import com.guild.ticket.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ITicketMapper {
    ITicketMapper INSTANCE = Mappers.getMapper(ITicketMapper.class);

    @Mapping(source = "payment_id", target = "codePayment")
    public TicketDTO modelToDTO(Ticket ticket);

    public Ticket dtoToModel(TicketDTO ticketDTO);
}
