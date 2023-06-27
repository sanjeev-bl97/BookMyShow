package com.example.bookMyShow.Transformers;

import com.example.bookMyShow.Dtos.RequestDto.MovieRequestDto;
import com.example.bookMyShow.Models.Movie;

public class MovieTransformer {

    public static Movie movieDtoToEntity(MovieRequestDto movieRequestDto){

        Movie movie = Movie.builder().name(movieRequestDto.getName()).genre(movieRequestDto.getGenre())
                .duration(movieRequestDto.getDuration()).rating(movieRequestDto.getRating())
                .language(movieRequestDto.getLanguage()).releaseDate(movieRequestDto.getReleaseDate())
                .build();

        return movie;
    }

}
