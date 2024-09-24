package gin.knox.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty
    @Size(min = 6, max = 64, message = "Incorrect username format")
    @Pattern(regexp = "^[\\da-zA-Z_-]+$", message = "Incorrect username format")
    private String username;

    @ToString.Exclude
    @NotEmpty
    @Size(min = 6, max = 64, message = "Incorrect password format")
    private String password;
}
