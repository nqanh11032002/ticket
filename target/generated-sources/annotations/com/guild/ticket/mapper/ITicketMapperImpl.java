package com.guild.ticket.mapper;

import com.guild.ticket.dto.TicketDTO;
import com.guild.ticket.entity.Ticket;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-25T15:23:28+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class ITicketMapperImpl implements ITicketMapper {

    @Override
    public TicketDTO modelToDTO(Ticket ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketDTO ticketDTO = new TicketDTO();

        ticketDTO.setCodePayment( ticket.getPayment_id() );
        ticketDTO.setId( ticket.getId() );
        ticketDTO.setShowTime_id( ticket.getShowTime_id() );
        ticketDTO.setSeat( ticket.getSeat() );
        ticketDTO.setNumSeat( ticket.getNumSeat() );

        return ticketDTO;
    }

    @Override
    public Ticket dtoToModel(TicketDTO ticketDTO) {
        if ( ticketDTO == null ) {
            return null;
        }

        Ticket ticket = new Ticket();

        ticket.setId( ticketDTO.getId() );
        ticket.setShowTime_id( ticketDTO.getShowTime_id() );
        ticket.setSeat( ticketDTO.getSeat() );
        ticket.setNumSeat( ticketDTO.getNumSeat() );

        return ticket;
    }
}
