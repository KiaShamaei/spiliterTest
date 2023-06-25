package snapp.pay.services;

import snapp.pay.dto.UserNetWorthDto;
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


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public User addNewUser(UserRequestDto userRequestDto) {

        User existingUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (existingUser != null) {
            throw new CustomerAlreadyExistsException("Customer with email id " + userRequestDto + " already exists");
        }
        User user = User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .contact(userRequestDto.getContact())
                .password(userRequestDto.getPassword())
                .build();
        userRepository.save(user);
        return user;
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto getUserByEmailId(String emailId) {

        User user = userRepository.findByEmail(emailId);
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .contact(user.getContact())
                .build();
        return userResponseDto;
    }
}
