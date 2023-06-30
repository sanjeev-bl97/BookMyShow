package com.example.bookMyShow.Controllers;

import com.example.bookMyShow.Dtos.RequestDto.MovieRequestDto;
import com.example.bookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody MovieRequestDto movieRequestDto){
        try{
            String result = movieService.addMovie(movieRequestDto);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCollectionOfMovie")
    public ResponseEntity<String> getCollectionOfMovie(@RequestParam String movieName){
        try{
            String result = movieService.getCollectionOfMovie(movieName);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
