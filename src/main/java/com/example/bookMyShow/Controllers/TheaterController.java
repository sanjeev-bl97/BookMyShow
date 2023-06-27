package com.example.bookMyShow.Controllers;

import com.example.bookMyShow.Dtos.RequestDto.TheaterRequestDto;
import com.example.bookMyShow.Dtos.RequestDto.TheaterSeatRequestDto;
import com.example.bookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/addTheater")
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        return  theaterService.addTheater(theaterRequestDto);
    }

    @PostMapping("/addTheaterSeats")
    public String addTheaterSeats(@RequestBody TheaterSeatRequestDto theaterSeatRequestDto){
        return  theaterService.addTheaterSeats(theaterSeatRequestDto);
    }
}
