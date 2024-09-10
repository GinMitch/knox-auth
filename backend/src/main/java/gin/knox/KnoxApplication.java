package gin.knox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "gin.knox")
public class KnoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnoxApplication.class, args);
    }
}
