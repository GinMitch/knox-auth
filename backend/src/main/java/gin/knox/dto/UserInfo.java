package gin.knox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
public class UserInfo {

    @NonNull
    private String username;
}
