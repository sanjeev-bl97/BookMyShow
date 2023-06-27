package com.example.bookMyShow.Services;


import com.example.bookMyShow.Dtos.RequestDto.ShowRequestDto;
import com.example.bookMyShow.Dtos.RequestDto.ShowSeatRequestDto;
import com.example.bookMyShow.Enums.SeatType;
import com.example.bookMyShow.Exception.MovieNotFound;
import com.example.bookMyShow.Exception.TheaterNotFound;
import com.example.bookMyShow.Models.*;
import com.example.bookMyShow.Repository.MovieRepository;
import com.example.bookMyShow.Repository.ShowRepository;
import com.example.bookMyShow.Repository.TheaterRepository;
import com.example.bookMyShow.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public String addShow(ShowRequestDto showRequestDto) throws MovieNotFound, TheaterNotFound {

        Optional<Movie> movieOptional = movieRepository.findById(showRequestDto.getMovieId());

        if(movieOptional.isEmpty())
            throw new MovieNotFound("Movie with "+ showRequestDto.getMovieId()+"not present");

        Optional<Theater> theaterOptional = theaterRepository.findById(showRequestDto.getTheaterId());

        if(theaterOptional.isEmpty())
            throw new TheaterNotFound("Theater with "+ showRequestDto.getTheaterId()+"not present");


        Movie movie = movieOptional.get();
        Theater theater = theaterOptional.get();
        Show show = ShowTransformer.showDtoToEntity(showRequestDto);

        show.setMovie(movie);
        show.setTheater(theater);

        show = showRepository.save(show);

        movie.getShowList().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show added successfully with id :"+show.getId();

    }

    public String associateSeats(ShowSeatRequestDto showSeatRequestDto) throws MovieNotFound {

       Optional<Show> showOptional = showRepository.findById(showSeatRequestDto.getShowId());

        if(showOptional.isEmpty())
            throw new MovieNotFound("Show with "+ showSeatRequestDto.getShowId()+"not present");

        Show show = showOptional.get();
        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterseatList();
        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(TheaterSeat theater1: theaterSeatList){

            ShowSeat showSeat = new ShowSeat();

            showSeat.setSeatNo(theater1.getSeatNo());
            showSeat.setShow(show);
            showSeat.setAvailable(true);
            showSeat.setFoodAttached(false);

            if(theater1.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setSeatType(SeatType.CLASSIC);
                showSeat.setPrice(showSeatRequestDto.getPriceOfClassicSeat());
            }
            else{
                showSeat.setSeatType(SeatType.PREMIUM);
                showSeat.setPrice(showSeatRequestDto.getPriceOfPremiumSeat());
            }

            showSeatList.add(showSeat);

        }

        showRepository.save(show);

        return "Show is associated with seats";
    }

    public String getMovieName() {

        Integer movieId = showRepository.getMostShowedMovie();

        return movieRepository.findById(movieId).get().getName();


    }

}
