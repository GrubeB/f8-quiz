package pl.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pl.app.user.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select (count(u) > 0) from User u where upper(u.email) = upper(:email)")
    boolean existsByEmailIgnoreCase(@Param("email") String email);
    @Query("select u from User u where upper(u.email) = upper(:email)")
    Optional<User> findByEmailIgnoreCase(@Param("email") @NonNull String email);
}
