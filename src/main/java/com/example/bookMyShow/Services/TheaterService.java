package com.example.bookMyShow.Services;

import com.example.bookMyShow.Dtos.RequestDto.TheaterRequestDto;
import com.example.bookMyShow.Dtos.RequestDto.TheaterSeatRequestDto;
import com.example.bookMyShow.Enums.SeatType;
import com.example.bookMyShow.Models.Theater;
import com.example.bookMyShow.Models.TheaterSeat;
import com.example.bookMyShow.Repository.TheaterRepository;
import com.example.bookMyShow.Transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    public String addTheater(TheaterRequestDto theaterRequestDto) {
        Theater theater = TheaterTransformer.TheaterDtoToEntity(theaterRequestDto);

        theaterRepository.save(theater);

        return "Theater added Successfully";
    }

    public String addTheaterSeats(TheaterSeatRequestDto theaterSeatRequestDto) {

        int col = theaterSeatRequestDto.getNoOfSeatsIn1Row();
        int noOfClassicSeats = theaterSeatRequestDto.getNofOfClassicSeats();
        int noOfPremiumSeats = theaterSeatRequestDto.getNoOfPremiumSeats();
        String location = theaterSeatRequestDto.getLocation();

        Theater theater = theaterRepository.findByLocation(location);
        List<TheaterSeat> theaterSeatList = theater.getTheaterseatList();

        int counter = 1;
        char cur = 'A';

        for(int i = 1; i <= noOfClassicSeats; i++){
            String seatNo = counter+"";
            seatNo += cur;

            cur++;

            if(cur-'A' == col) {
                cur = 'A';
                counter++;
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeat.setTheater(theater);

            theaterSeatList.add(theaterSeat);

        }

        for(int i = 1; i <= noOfPremiumSeats; i++){
            String seatNo = counter+"";
            seatNo += cur;

            cur++;

            if(cur-'A' == col) {
                cur = 'A';
                counter++;
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setTheater(theater);

            theaterSeatList.add(theaterSeat);

        }

        theaterRepository.save(theater);

        return "Theater Seats have been successfully added";
    }

    public int getCountByTheater(String theaterName) {
        return theaterRepository.getCountByName(theaterName);
    }

}
