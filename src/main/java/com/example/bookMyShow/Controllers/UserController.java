package com.example.bookMyShow.Controllers;

import com.example.bookMyShow.Dtos.RequestDto.UserRequestDto;
import com.example.bookMyShow.Dtos.ResponseDto.TicketResponseDto;
import com.example.bookMyShow.Dtos.ResponseDto.UserResponseDto;
import com.example.bookMyShow.Exception.UserNotFound;
import com.example.bookMyShow.Models.User;
import com.example.bookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserRequestDto user){

        try{
            String message = userService.addUser(user);
            return message;
        }
        catch (Exception e){
           return e.getMessage();
        }

    }

    @GetMapping("/getOldestUser")
    public UserResponseDto getOldestUser()throws UserNotFound {

        try{
            UserResponseDto user = userService.getOldestUser();

            user.setStatusCode("200");
            user.setStatusMessage("SUCCESS");
            return user;
        }
        catch (Exception e){
            UserResponseDto user = new UserResponseDto();

            user.setStatusCode("500");
            user.setStatusMessage("FAILURE"+e.getMessage());
            return user;
        }

    }

    @GetMapping("/findUsersGreaterThanAge")
    public List<UserResponseDto> getAllUsers(@RequestParam("age")Integer age){
        return userService.getAllUserGreaterThan(age);
    }

    @GetMapping("/getAllTicketByUser")
    public List<TicketResponseDto> getAllTicketByUser(@RequestParam("id")Integer id){
        return userService.getAllTicketByUser(id);
    }

}
