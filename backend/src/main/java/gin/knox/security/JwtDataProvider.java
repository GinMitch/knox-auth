package gin.knox.security;

import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.lang.NonNull;

public interface JwtDataProvider extends RSAKeyProvider {

    @NonNull
    String getIssuer();
}
