package gin.knox.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TokenData implements Serializable {

    @NonNull
    private String userId;

    @Nullable
    private String userType;
}
