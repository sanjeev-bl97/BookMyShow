package com.example.bookMyShow.Dtos.RequestDto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ShowRequestDto {
    private String time;

    private Date date;

    private Integer movieId;

    private Integer theaterId;

}
