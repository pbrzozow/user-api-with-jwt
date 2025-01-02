package rest.with.jwt.user_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import rest.with.jwt.user_api.controller.UserController;
import rest.with.jwt.user_api.domain.dto.CreateUserDto;
import rest.with.jwt.user_api.domain.dto.UserDto;
import rest.with.jwt.user_api.service.UserService;

import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldReturnAllUsers() {
        //Arange
        List<UserDto> users = List.of(new UserDto(1L,"Kasia"),new UserDto(2L,"Antek"));
        Mockito.when(userService.getAllUsers()).thenReturn(users);
        //Act
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();
        //Assert
        assert(response.getStatusCode()).equals(HttpStatus.OK);
        assert Objects.equals(response.getBody(), users);
    }
    @Test
    @WithMockUser(username = "kasia")
    void shouldCreateUser(){
        //Arrange
        CreateUserDto createUserDto = new CreateUserDto("aga","pass");
        UserDto userDto = new UserDto(1L,"aga");
        when(userService.createUser(createUserDto)).thenReturn(userDto);
        //Act
        ResponseEntity<?> responseEntity = userController.registerUser(createUserDto);
        //Assert
        assert(Objects.equals(responseEntity.getBody(), "Account has been succesfully created!"));
        assert(responseEntity.getStatusCode().equals(HttpStatus.OK));
    }
}
