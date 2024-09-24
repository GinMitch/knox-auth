package gin.knox.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AnyResult<T> {

    private String error;

    private T result;
}
