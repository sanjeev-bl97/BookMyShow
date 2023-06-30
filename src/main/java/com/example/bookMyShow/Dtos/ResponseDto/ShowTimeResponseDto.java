package com.example.bookMyShow.Dtos.ResponseDto;

import lombok.Data;

import java.util.List;

@Data

public class ShowTimeResponseDto {
        private List<String> name;
        private int statusCode;
        private String statusMessage;
    }

