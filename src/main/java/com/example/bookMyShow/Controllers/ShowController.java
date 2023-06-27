package com.example.bookMyShow.Controllers;

import com.example.bookMyShow.Dtos.RequestDto.ShowRequestDto;
import com.example.bookMyShow.Dtos.RequestDto.ShowSeatRequestDto;
import com.example.bookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/addShow")
    public ResponseEntity<String> addShow(@RequestBody ShowRequestDto showRequestDto){
        try{
            String result = showService.addShow(showRequestDto);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associateSeats")
    public ResponseEntity<String> associateSeats(@RequestBody ShowSeatRequestDto showSeatRequestDto){
        try{
            String result = showService.associateSeats(showSeatRequestDto);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/most-recommended-movie-name")
    public String getMovieName(){

        return showService.getMovieName();
    }



}
