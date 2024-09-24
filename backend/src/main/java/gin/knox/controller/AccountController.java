package gin.knox.controller;

import gin.knox.dto.UserInfo;
import gin.knox.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Tag(name = "account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> getUser() {
        UserInfo user = this.accountService.getUser();
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @GetMapping(value = "/check/public", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getPublic() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("This data is available to anyone.");
    }
}
