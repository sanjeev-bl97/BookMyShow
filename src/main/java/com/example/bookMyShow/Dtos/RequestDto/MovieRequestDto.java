package com.example.bookMyShow.Dtos.RequestDto;

import com.example.bookMyShow.Enums.Genre;
import com.example.bookMyShow.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {

    private String name;
    private double duration;
    private double rating;
    private Genre genre;
    private Language language;
    private Date releaseDate;

}
