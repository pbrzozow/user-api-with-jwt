package rest.with.jwt.user_api.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.with.jwt.user_api.domain.User;
import rest.with.jwt.user_api.domain.UserMapper;
import rest.with.jwt.user_api.domain.dto.CreateUserDto;
import rest.with.jwt.user_api.domain.dto.UpdateUserDto;
import rest.with.jwt.user_api.domain.dto.UserDto;
import rest.with.jwt.user_api.exception.UserNotFoundException;
import rest.with.jwt.user_api.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.toEntity(createUserDto);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return userMapper.toDto(user);
    }
    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
    @Transactional
    public UserDto updateUser(UpdateUserDto updateUserDto,Long id){
        String newUsername = updateUserDto.getUsername();
        String newPassword = updateUserDto.getPassword();
        User user = userRepository
                .findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id "+id+ " does not exists"));
        if (newUsername!=null){
            user.setUsername(newUsername);}
        if (newPassword!=null){
            user.setPassword(passwordEncoder.encode(newPassword));}
        userRepository.save(user);
        return userMapper.toDto(user);
    }
    public UserDto findUserById(Long id){
        User user = userRepository
                .findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id "+id+ " does not exists"));
        return userMapper.toDto(user);
    }
}
