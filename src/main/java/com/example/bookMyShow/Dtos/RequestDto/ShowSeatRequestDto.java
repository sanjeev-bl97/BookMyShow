package com.example.bookMyShow.Dtos.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatRequestDto {

    private Integer showId;
    private Integer priceOfClassicSeat;
    private Integer priceOfPremiumSeat;

}
