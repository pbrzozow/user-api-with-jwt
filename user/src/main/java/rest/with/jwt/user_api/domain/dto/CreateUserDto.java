package rest.with.jwt.user_api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


public class CreateUserDto {
    @NonNull
    private String username;
    @NonNull
    private String password;

    public CreateUserDto(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }


    public @NonNull String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
