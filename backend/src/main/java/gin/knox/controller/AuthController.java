package gin.knox.controller;

import gin.knox.dto.CookiesResult;
import gin.knox.dto.LoginRequest;
import gin.knox.dto.SignupRequest;
import gin.knox.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(
            @RequestBody @Valid @NotNull LoginRequest request,
            HttpServletResponse res
    ) {
        CookiesResult result = this.authService.login(request);
        if (result.getError() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getError());
        }

        res.addCookie(result.getResult()[0]);
        res.addCookie(result.getResult()[1]);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signup(
            @RequestBody @Valid @NotNull SignupRequest request,
            HttpServletResponse res
    ) {
        CookiesResult result = this.authService.signup(request);
        if (result.getError() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getError());
        }

        res.addCookie(result.getResult()[0]);
        res.addCookie(result.getResult()[1]);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
