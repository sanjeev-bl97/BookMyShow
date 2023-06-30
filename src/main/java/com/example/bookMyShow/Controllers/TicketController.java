package com.example.bookMyShow.Controllers;

import com.example.bookMyShow.Dtos.RequestDto.TicketRequestDto;
import com.example.bookMyShow.Dtos.ResponseDto.TicketResponseDto;
import com.example.bookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/bookTicket")
    public ResponseEntity<TicketResponseDto> bookTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        try {
            TicketResponseDto response = ticketService.bookTicket(ticketRequestDto);
            response.setResponseMessage("Ticket booked successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            TicketResponseDto ticketResponseDto = new TicketResponseDto();
            ticketResponseDto.setResponseMessage(e.getMessage());
            return new ResponseEntity<>(ticketResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cancelTicket")
    public ResponseEntity<String> cancelTicket(@RequestBody Integer id) {
       ticketService.cancelTicket(id);
        return new ResponseEntity<>("Ticket Cancelled Successfully",HttpStatus.OK);
    }

}
