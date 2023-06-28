package snapp.pay.services;



import snapp.pay.dto.UserRequestDto;
import snapp.pay.dto.UserResponseDto;
import snapp.pay.entities.User;
import java.util.List;


/**
 *
 * @Author Kiarash Shamaei 2023-06-25
 */

public interface UserService {

    UserResponseDto addNewUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUser(Long userId);

    UserResponseDto getUserByEmailId(String emailId);

}
