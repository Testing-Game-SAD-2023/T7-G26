package requirement_t7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * Launch the application
 *
 * @author Pedro Zahonero Mangas
 * @author Pablo Garcia Fernandez
 * @version 1.0
 * @since 2023-04-15
 */
@SpringBootApplication
@EnableOpenApi
public class T7Application {
    public static void main(String[] args){
        SpringApplication.run(T7Application.class, args);
    }

}
