package rest.with.jwt.user_api.domain;


import org.springframework.stereotype.Component;
import rest.with.jwt.user_api.domain.dto.CreateUserDto;
import rest.with.jwt.user_api.domain.dto.UserDto;

@Component
public class UserMapper {
   public UserDto toDto(User user){
      UserDto userDto = new UserDto();
      userDto.setId(user.getId());
      userDto.setUsername(user.getUsername());
      return userDto;
   }

   public User toEntity(CreateUserDto createUser){
       User user = new User();
       user.setUsername(createUser.getUsername());
       user.setPassword(createUser.getPassword());
       return user;
   }
}
