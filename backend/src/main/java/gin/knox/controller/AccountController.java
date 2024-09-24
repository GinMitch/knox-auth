package gin.knox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    @GetMapping(value = "/check/public", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getPublic() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("This data is available to anyone.");
    }
}
