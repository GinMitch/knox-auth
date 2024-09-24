package gin.knox.service;

import gin.knox.dto.CookiesResult;
import gin.knox.dto.LoginRequest;
import gin.knox.dto.SignupRequest;
import gin.knox.enums.UserType;
import gin.knox.jpa.User;
import gin.knox.jpa.UserRepo;
import gin.knox.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public CookiesResult login(@NonNull LoginRequest request) {
        Optional<User> userMaybe = this.userRepo.findFirstByUsernameLikeIgnoreCase(request.getUsername());
        if (userMaybe.isEmpty()) {
            return CookiesResult.error("user_not_found");
        }

        User user = userMaybe.get();
        if (!this.passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return CookiesResult.error("password_mismatch");
        }

        return CookiesResult.success(
                this.jwtService.issueCookies(
                        user.getId().toString(),
                        user.getType().toString()
                ));
    }

    public CookiesResult signup(@NonNull SignupRequest request) {
        Optional<User> userMaybe = this.userRepo.findFirstByUsernameLikeIgnoreCase(request.getUsername());
        if (userMaybe.isPresent()) {
            return CookiesResult.error("user_exists");
        }

        User user =
                new User(
                        request.getUsername(),
                        this.passwordEncoder.encode(request.getPassword()),
                        request.isAdmin() ? UserType.admin : UserType.user);
        this.userRepo.save(user);

        return CookiesResult.success(
                this.jwtService.issueCookies(
                        user.getId().toString(),
                        user.getType().toString()
                ));
    }

    public CookiesResult logout() {
        return CookiesResult.success(
                this.jwtService.revokeCookies()
        );
    }
}
