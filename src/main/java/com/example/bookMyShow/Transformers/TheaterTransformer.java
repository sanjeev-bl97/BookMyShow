package com.example.bookMyShow.Transformers;

import com.example.bookMyShow.Dtos.RequestDto.TheaterRequestDto;
import com.example.bookMyShow.Models.Theater;

public class TheaterTransformer {

    public static Theater TheaterDtoToEntity(TheaterRequestDto theaterRequestDto){
        Theater theater=Theater.builder().name(theaterRequestDto.getName())
                .location(theaterRequestDto.getLocation()).build();
        return theater;
    }

}
