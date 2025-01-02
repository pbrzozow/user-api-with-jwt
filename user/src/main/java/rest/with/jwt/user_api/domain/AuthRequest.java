package rest.with.jwt.user_api.domain;

import lombok.*;


public class AuthRequest {
    @NonNull private String username;
    @NonNull private String password;

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
