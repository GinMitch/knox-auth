package gin.knox.service;

import gin.knox.dto.CookiesResult;
import gin.knox.dto.LoginRequest;
import gin.knox.dto.SignupRequest;
import org.springframework.lang.NonNull;

public interface AuthService {

    CookiesResult login(@NonNull LoginRequest request);

    CookiesResult signup(@NonNull SignupRequest request);

    CookiesResult logout();
}
