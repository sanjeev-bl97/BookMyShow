package com.example.bookMyShow.Transformers;

import com.example.bookMyShow.Dtos.ResponseDto.TicketResponseDto;
import com.example.bookMyShow.Models.Show;
import com.example.bookMyShow.Models.Ticket;

public class TicketTransformer {


    public static TicketResponseDto createTicketResponseDto(Show show,Ticket ticket){

        TicketResponseDto ticketResponseDto = TicketResponseDto.builder()
                .bookedSeats(ticket.getBookedSeats())
                .location(show.getTheater().getLocation())
                .theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getName())
                .showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .totalPrice(ticket.getTotalPrice())
                .build();

        return ticketResponseDto;
    }

}
