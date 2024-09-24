package gin.knox.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<User> findFirstById(@NonNull UUID id);

    Optional<User> findFirstByUsernameLikeIgnoreCase(@NonNull String username);
}
