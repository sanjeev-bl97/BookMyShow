package com.example.bookMyShow.Dtos.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {

    private String showTime;

    private Date showDate;

    private String movieName;

    private String theaterName;

    private String bookedSeats;

    private String responseMessage;

    private  String location;

    private int totalPrice;


}
