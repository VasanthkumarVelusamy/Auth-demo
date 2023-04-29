package com.vasanth.authdemo.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    UsersRepository usersRepository;
    ModelMapper modelMapper;

    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponseDto createUser(CreateUserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return modelMapper.map(usersRepository.save(userEntity), UserResponseDto.class);
    }

    public UserResponseDto verifyUser(LoginUserDto loginUserDto) {
        UserEntity userEntity = usersRepository.findByUsername(loginUserDto.getUsername());
        return modelMapper.map(userEntity, UserResponseDto.class);
    }
}
