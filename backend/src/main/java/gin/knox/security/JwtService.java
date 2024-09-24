package gin.knox.security;

import jakarta.servlet.http.Cookie;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface JwtService {

    @Nullable
    Cookie[] issueCookies(@NonNull String userId, @NonNull String userType);

    @Nullable
    TokenData parseCookies(Cookie[] cookies);
}
