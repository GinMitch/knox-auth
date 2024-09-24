package gin.knox.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private static final String authCookieName = "auth_tkn";
    private static final String markerCookieName = "auth_ok";
    private static final String claimUserType = "xut";
    private static final int sessionTtl = 43200;

    private final JwtDataProvider dataProvider;
    private Algorithm algorithm;
    private JWTVerifier verifier;

    @PostConstruct
    private void postConstruct() {
        this.algorithm = Algorithm.RSA256(this.dataProvider);
        this.verifier = JWT.require(this.algorithm).withIssuer(this.dataProvider.getIssuer()).build();
    }

    public @Nullable Cookie[] issueCookies(@NonNull String userId, @NonNull String userType) {
        String token = issueToken(userId, userType);
        return token == null ? null : new Cookie[]{
                this.createAuth(token, sessionTtl),
                this.createMarker("1", sessionTtl - 10)
        };
    }

    private @NonNull Cookie createAuth(@Nullable String value, int maxAge) {
        Cookie cookie = new Cookie(authCookieName, value);
        // cookie.setDomain(""); // todo: set domain from an env variable
        // cookie.setSecure(true); // todo: set true when deploying
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    private @NonNull Cookie createMarker(@Nullable String value, int maxAge) {
        Cookie cookie = new Cookie(markerCookieName, value);
        // cookie.setDomain(""); // todo: set domain from an env variable
        // cookie.setSecure(true); // todo: set true when deploying
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    private @Nullable String issueToken(@NonNull String userId, @NonNull String userType) {
        try {
            return JWT.create()
                    .withKeyId(this.dataProvider.getPrivateKeyId())
                    .withIssuer(this.dataProvider.getIssuer())
                    .withExpiresAt(Instant.now().plusSeconds(sessionTtl))
                    .withSubject(userId)
                    .withClaim(claimUserType, userType)
                    .sign(this.algorithm);
        } catch (Exception ex) {
            return null;
        }
    }

    public @Nullable TokenData parseCookies(Cookie[] cookies) {
        Optional<Cookie> cookieMaybe =
                cookies == null
                        ? Optional.empty()
                        : Arrays.stream(cookies)
                        .filter(cookie -> cookie.getName().equals(authCookieName))
                        .findFirst();

        TokenData tokenData = cookieMaybe.isEmpty() || cookieMaybe.get().getValue().isBlank()
                ? null
                : this.parseToken(cookieMaybe.get().getValue());

        return tokenData == null || tokenData.getUserType() == null ? null : tokenData;
    }

    private @Nullable TokenData parseToken(@NonNull String token) {
        try {
            DecodedJWT jwt = this.verifier.verify(token);
            return new TokenData(jwt.getSubject(), jwt.getClaim(claimUserType).asString());
        } catch (Exception ex) {
            return null;
        }
    }
}
