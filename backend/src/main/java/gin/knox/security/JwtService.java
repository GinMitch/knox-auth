package gin.knox.security;

import jakarta.servlet.http.Cookie;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface JwtService {

    @Nullable
    Cookie[] issueCookies(@NonNull String userId, @NonNull String userType);

    @NonNull
    Cookie[] revokeCookies();

    @Nullable
    TokenData parseCookies(Cookie[] cookies);
}
