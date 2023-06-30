package com.example.bookMyShow.Services;

import com.example.bookMyShow.Dtos.RequestDto.MovieRequestDto;
import com.example.bookMyShow.Models.Movie;
import com.example.bookMyShow.Models.Show;
import com.example.bookMyShow.Models.Ticket;
import com.example.bookMyShow.Repository.MovieRepository;
import com.example.bookMyShow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto) {
        Movie movie = MovieTransformer.movieDtoToEntity(movieRequestDto);

        movieRepository.save(movie);

        return "Movie added Successfully";
    }

    public String getCollectionOfMovie(String movieName) {
        List<Show> showList = movieRepository.findByName(movieName).getShowList();
        double result = 0;

        for(Show show: showList)
            for(Ticket ticket : show.getTicketList())
                result += ticket.getTotalPrice();

        return String.valueOf(result);

    }

}
