package gin.knox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "gin.knox")
@EntityScan({"gin.knox.jpa"})
@EnableJpaRepositories({"gin.knox.jpa"})
public class KnoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnoxApplication.class, args);
    }
}
