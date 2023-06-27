package com.example.bookMyShow.Transformers;

import com.example.bookMyShow.Dtos.RequestDto.ShowRequestDto;
import com.example.bookMyShow.Models.Show;

public class ShowTransformer {

    public static Show showDtoToEntity(ShowRequestDto showRequestDto){

        Show show=Show.builder().showDate(showRequestDto.getDate()).showTime(showRequestDto.getTime()).build();
        return show;
    }

}
