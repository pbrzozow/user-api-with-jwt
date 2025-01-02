package rest.with.jwt.user_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import rest.with.jwt.user_api.config.JwtUtil;
import rest.with.jwt.user_api.domain.AuthRequest;
import rest.with.jwt.user_api.domain.dto.CreateUserDto;
import rest.with.jwt.user_api.domain.dto.UpdateUserDto;
import rest.with.jwt.user_api.domain.dto.UserDto;
import rest.with.jwt.user_api.exception.IncorrectCredentialsException;
import rest.with.jwt.user_api.service.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Map;


@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody  CreateUserDto createUserDto){
        userService.createUser(createUserDto);
        return ResponseEntity.ok("Account has been successfully created!");
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest){
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        authManager.authenticate(authInputToken);
        String token = jwtUtil.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(Map.of("token",token));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto userDto,@PathVariable Long id, Principal principal){
        UserDto userBeforeUpdate = userService.findUserById(id);
        if (!principal.getName().equals(userBeforeUpdate.getUsername())){
            throw new IncorrectCredentialsException("Access denied");
        }
        UserDto userAfterUpdate = userService.updateUser(userDto, id);
        return ResponseEntity.ok(userAfterUpdate);
    }
}
