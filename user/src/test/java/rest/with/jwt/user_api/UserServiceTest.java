package rest.with.jwt.user_api;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import rest.with.jwt.user_api.domain.User;
import rest.with.jwt.user_api.domain.UserMapper;
import rest.with.jwt.user_api.domain.dto.CreateUserDto;
import rest.with.jwt.user_api.domain.dto.UpdateUserDto;
import rest.with.jwt.user_api.domain.dto.UserDto;
import rest.with.jwt.user_api.repository.UserRepository;
import rest.with.jwt.user_api.service.UserService;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;
    @Test
    void shouldReturnAllUsers(){
        //Arange
        User john = new User(1L, "John","pass");
        UserDto johnDto = new UserDto(1L, "John");
        User natalia =  new User(2L, "Natalia","pass");
        UserDto nataliaDto = new UserDto(2L, "Natalia");
        List<UserDto> allUsers = List.of(johnDto,nataliaDto);
        List<User> users =List.of(john,natalia);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(userMapper.toDto(john)).thenReturn(johnDto);
        Mockito.when(userMapper.toDto(natalia)).thenReturn(nataliaDto);
        //Act
        List<UserDto> result = userService.getAllUsers();
        //Assert
        Mockito.verify(userRepository).findAll();
        assert(result).equals(allUsers);
        assert(result).contains(johnDto);
        assert(result).contains(nataliaDto);
    }

    @Test
    void shouldSuccesfullyCreateUser(){
        //Arrange
        CreateUserDto createUserDto = new CreateUserDto("aga","pass");
        User user = new User(1L,"aga","pass");
        UserDto userDto = new UserDto(1L,"aga");
        Mockito.when(passwordEncoder.encode("pass")).thenReturn("hash");
        Mockito.when(userMapper.toEntity(createUserDto)).thenReturn(user);
        Mockito.when(userMapper.toDto(user)).thenReturn(userDto);
        //Act
        UserDto result = userService.createUser(createUserDto);
        //Assert
        Mockito.verify(userRepository).save(user);
        assert(result.getUsername()).equals(userDto.getUsername());
        assert(result.getId()).equals(userDto.getId());

    }
    @Test
    void updateShouldThrowExceptionWhenUserDoesNotExist(){
        //Arrange
        UpdateUserDto userDto = new UpdateUserDto("aga",null);
        Mockito.when(userRepository.findUserById(1L)).thenReturn(Optional.empty());


        //Act and Assert
        assertThrows(RuntimeException.class,() -> userService.updateUser(userDto, 1L));
    }

}
