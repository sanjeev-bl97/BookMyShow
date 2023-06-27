package com.example.bookMyShow.Transformers;

import com.example.bookMyShow.Dtos.RequestDto.UserRequestDto;
import com.example.bookMyShow.Dtos.ResponseDto.UserResponseDto;
import com.example.bookMyShow.Models.User;

public class UserTransformer {

    public static User UserDtoToEntity(UserRequestDto userRequestDto){

        User user=User.builder().age(userRequestDto.getAge())
                .emailId(userRequestDto.getEmailId()).mobileNo(userRequestDto.getMobileNo())
                .name(userRequestDto.getName()).build();

        return user;

    }

    public static UserResponseDto UserEntityToDto(User user){
        UserResponseDto userResponseDto = UserResponseDto.builder().age(user.getAge()).name(user.getName())
                .mobNo(user.getMobileNo()).build();
        return userResponseDto;
    }

}
