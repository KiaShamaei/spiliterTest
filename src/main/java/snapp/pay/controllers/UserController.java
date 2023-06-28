package snapp.pay.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import snapp.pay.dto.UserNetWorthDto;
import snapp.pay.dto.UserResponseDto;
import snapp.pay.dto.UsersAllGangsDto;
import snapp.pay.services.UserAssetLiabilitiesService;
import snapp.pay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * UserController handle user request
 * @Author Kiarash Shamaei 2023-06-25
 */
@RestController
@RequestMapping(value = "/api/v1/")
@Tag(name = "User Api")
public class UserController  {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAssetLiabilitiesService userAssetLiabilitiesService;



    @GetMapping(value = "/user")
    @Operation(summary = "this mehtod return all users " ,description = "List<UserResponseDto>")
    public ResponseEntity<List<UserResponseDto>> getUser() {

        List<UserResponseDto> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userid}")
    @Operation(summary = "this mehtod return a users by id" ,description = "UserResponseDto")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(value = "userid") Long userId) {

        UserResponseDto user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/user/email")
    @Operation(summary = "this mehtod return a users by email" ,description = "UserResponseDto")
    public ResponseEntity<UserResponseDto> getUserByEmailId(@RequestParam(value = "emailId") String emailId) {

        UserResponseDto user = userService.getUserByEmailId(emailId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userid}/worth")
    @Operation(summary = "this mehtod return a worth users by id" ,description = "UserNetWorthDto")
    public ResponseEntity<UserNetWorthDto> getMyNetWorth(@PathVariable(value = "userid") Long userId) {

        UserNetWorthDto userNetWorth = userAssetLiabilitiesService.getMyNetWorth(userId);
        return new ResponseEntity<>(userNetWorth, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userid}/gangs")
    @Operation(summary = "this method return all group users by user id" ,description = "UsersAllGangsDto")
    public ResponseEntity<UsersAllGangsDto> getAllMyGangs(@PathVariable(value = "userid") Long userId) {

        UsersAllGangsDto userNetWorth = userAssetLiabilitiesService.getMyGroupwiseNetWorth(userId);
        return new ResponseEntity<>(userNetWorth, HttpStatus.OK);
    }
//    @GetMapping(value = "/test")
//    @Operation(summary = "this method " ,description = "UsersAllGangsDto")
//    public String getTest() {
//        return"Hello world";
//    }

}
