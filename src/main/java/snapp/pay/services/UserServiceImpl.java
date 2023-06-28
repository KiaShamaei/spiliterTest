package snapp.pay.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import snapp.pay.dto.UserRequestDto;
import snapp.pay.dto.UserResponseDto;
import snapp.pay.entities.User;
import snapp.pay.exceptions.CustomerAlreadyExistsException;
import snapp.pay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *  UserService  do business of user
 * @Author Kiarash Shamaei 2023-06-25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDto addNewUser(UserRequestDto userRequestDto) {

        var existingUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (existingUser.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with email id " + userRequestDto + " already exists");
        }
        var user = this.saveUser(userRequestDto);
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .contact(user.getContact())
                .build();
    }
    //save user
    private User saveUser(UserRequestDto dto){
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .contact(dto.getContact())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        userRepository.save(user);
        return user;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtoList = new ArrayList<>();
        for (User usr : users) {
            UserResponseDto dto = UserResponseDto.builder()
                    .id(usr.getId())
                    .name(usr.getName())
                    .email(usr.getEmail())
                    .contact(usr.getContact())
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }


    @Override
    public UserResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId).get();
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(userId)
                .name(user.getName())
                .email(user.getEmail())
                .contact(user.getContact())
                .build();
        return userResponseDto;
    }


    @Override
    public UserResponseDto getUserByEmailId(String emailId) {

        User user = userRepository.findByEmail(emailId).orElseThrow(
                ()->new CustomerAlreadyExistsException("Customer with email id " + emailId + " already exists"));
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .contact(user.getContact())
                .build();
        return userResponseDto;
    }
}
