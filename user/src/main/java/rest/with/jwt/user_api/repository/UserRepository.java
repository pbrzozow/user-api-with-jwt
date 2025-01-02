package rest.with.jwt.user_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.with.jwt.user_api.domain.User;
import rest.with.jwt.user_api.domain.dto.UserDto;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserById(Long id);

    Optional<User> findUserByUsername(String username);
}
