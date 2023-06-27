package com.example.bookMyShow.Dtos.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDto {
    private Integer showId;

    private Integer userId;

    private List<String> reqSeatList;


}
