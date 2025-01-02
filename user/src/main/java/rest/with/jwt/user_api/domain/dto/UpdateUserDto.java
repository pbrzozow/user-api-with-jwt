package rest.with.jwt.user_api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UpdateUserDto {
    private String username;
    private String password;

    public UpdateUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UpdateUserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
