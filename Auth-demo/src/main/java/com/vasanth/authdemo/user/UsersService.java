package com.vasanth.authdemo.user;

import com.vasanth.authdemo.user.auth.AuthService;
import com.vasanth.authdemo.user.auth.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    UsersRepository usersRepository;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;
    AuthService authService;
    JwtService jwtService;

    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthService authService, JwtService jwtService) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.jwtService = jwtService;
    }

    public UserResponseDto createUser(CreateUserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity savedUser = usersRepository.save(userEntity);
        UserResponseDto userResponseDto = modelMapper.map(savedUser, UserResponseDto.class);
        // OPTION 1:
        // userResponseDto.setToken(authService.createToken(savedUser));
        //
        // OPTION 2:
        String token = jwtService.createJwt(savedUser.getUsername());
        //
        userResponseDto.setToken(token);
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
        // OPTION 1:
        // userResponseDto.setToken(authService.createToken(userEntity).toString());
        //
        // OPTION 2:
        String token = jwtService.createJwt(userEntity.getUsername());
        userResponseDto.setToken(token);
        //
        return userResponseDto;
    }
}
