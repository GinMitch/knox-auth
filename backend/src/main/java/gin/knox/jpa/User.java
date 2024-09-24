package gin.knox.jpa;

import gin.knox.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.lang.NonNull;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Setter
    @NonNull
    @Column(name = "username", nullable = false, unique = true, length = 64)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String username;

    @Setter
    @NonNull
    @Column(name = "password", nullable = false, length = 64)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String password;

    @Setter
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 16)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UserType type;

    public User(@NonNull String username, @NonNull String password, @NonNull UserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
}
