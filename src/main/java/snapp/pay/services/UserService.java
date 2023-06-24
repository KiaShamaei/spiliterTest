package snapp.pay.services;


import snapp.pay.dto.UserNetWorthDto;
import snapp.pay.dto.UserRequestDto;
import snapp.pay.dto.UserResponseDto;
import snapp.pay.entities.User;

import java.util.List;


public interface UserService {

    User addNewUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUser(Long userId);

    UserResponseDto getUserByEmailId(String emailId);

}
