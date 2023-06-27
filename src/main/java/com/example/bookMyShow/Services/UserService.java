package com.example.bookMyShow.Services;

import com.example.bookMyShow.Dtos.RequestDto.UserRequestDto;
import com.example.bookMyShow.Dtos.ResponseDto.UserResponseDto;
import com.example.bookMyShow.Exception.UserNotFound;
import com.example.bookMyShow.Models.User;
import com.example.bookMyShow.Repository.UserRepository;
import com.example.bookMyShow.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserRequestDto user) {
        User user1 = UserTransformer.UserDtoToEntity(user);

        userRepository.save(user1);

        return "User has been added successfully ";
    }

    public UserResponseDto getOldestUser() throws UserNotFound {

        List<User> userList = userRepository.findAll();

        Integer max = 0;
        User user = null;

        for(User user1 : userList){
            if(max < user1.getAge()){
                max = user1.getAge();
                user = user1;
            }
        }

        if(user == null)
            throw new UserNotFound("User NOt found");

        UserResponseDto userResponseDto = UserTransformer.UserEntityToDto(user);

        return userResponseDto;
    }

    public List<UserResponseDto> getAllUserGreaterThan(Integer age) {
        List<User> userList= userRepository.findUserWithAgeGreater(age);
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for(User user: userList)
            userResponseDtoList.add(UserTransformer.UserEntityToDto(user));

        return userResponseDtoList;
    }

}
