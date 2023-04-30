package com.vasanth.authdemo.user;

import com.vasanth.authdemo.user.auth.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    UsersRepository usersRepository;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;
    AuthService authService;

    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthService authService) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    public UserResponseDto createUser(CreateUserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity savedUser = usersRepository.save(userEntity);
        UserResponseDto userResponseDto = modelMapper.map(savedUser, UserResponseDto.class);
        userResponseDto.setToken(authService.createToken(savedUser));
        return userResponseDto;
    }

    public UserResponseDto verifyUser(LoginUserDto loginUserDto) {
        UserEntity userEntity = usersRepository.findByUsername(loginUserDto.getUsername());

        if (userEntity == null) {
            throw new RuntimeException("user not found");
        }

        if (!passwordEncoder.matches(loginUserDto.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("incorrect password");
        }
        UserResponseDto userResponseDto = modelMapper.map(userEntity, UserResponseDto.class);

        userResponseDto.setToken(authService.createToken(userEntity).toString());

        return userResponseDto;
    }
}
