package com.example.bookMyShow.Controllers;

import com.example.bookMyShow.Dtos.RequestDto.ShowRequestDto;
import com.example.bookMyShow.Dtos.RequestDto.ShowSeatRequestDto;
import com.example.bookMyShow.Dtos.RequestDto.ShowTimeRequestDto;
import com.example.bookMyShow.Dtos.ResponseDto.ShowTimeResponseDto;
import com.example.bookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/mostRecommendedMovie")
    public String getMovieName(){

        return showService.getMovieName();
    }
    @GetMapping("/getShowTime")
    public ResponseEntity<ShowTimeResponseDto> getShowTime(@RequestBody ShowTimeRequestDto showTimeRequestDto){

        try{
            ShowTimeResponseDto result = showService.getShowTime(showTimeRequestDto);
             result.setStatusCode(HttpStatus.OK.value());
             result.setStatusMessage("SUCCESS");

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            ShowTimeResponseDto result = new ShowTimeResponseDto();
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getTheaterListAtParticularShow")
    public ResponseEntity<List<String>> getTheaterListAtParticularShow(@RequestParam String showTime){

        try{
            List<String> result = showService.getTheaterList(showTime);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            List<String> result = new ArrayList<>();
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
    }




}
