package rest.with.jwt.user_api.domain;

import lombok.*;


@Value
public class AuthRequest {
    @NonNull private String username;
    @NonNull private String password;
}
