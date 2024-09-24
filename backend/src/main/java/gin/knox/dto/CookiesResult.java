package gin.knox.dto;

import jakarta.servlet.http.Cookie;

public class CookiesResult extends AnyResult<Cookie[]> {

    public CookiesResult(String error, Cookie[] result) {
        super(error, result);
    }

    public static CookiesResult error(String error) {
        return new CookiesResult(error, null);
    }

    public static CookiesResult success(Cookie[] result) {
        return new CookiesResult(null, result);
    }
}
