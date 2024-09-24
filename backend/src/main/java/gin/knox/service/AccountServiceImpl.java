package gin.knox.service;

import gin.knox.dto.UserInfo;
import gin.knox.jpa.User;
import gin.knox.jpa.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepo userRepo;

    public UserInfo getUser() {
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userMaybe = this.userRepo.findFirstById(UUID.fromString(principal));
        if (userMaybe.isEmpty()) {
            throw new RuntimeException("user_not_found");
        }

        User user = userMaybe.get();
        return new UserInfo(user.getUsername());
    }
}
