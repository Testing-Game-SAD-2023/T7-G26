package requirement_t7;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**

 This class is a configuration class for Swagger documentation in the T7-G26 application.

 It is annotated with @Configuration and @EnableSwagger2 to enable Swagger support.

 It provides a Docket bean to configure the Swagger API documentation.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**

     Creates and configures a Docket bean for Swagger documentation.
     It specifies the base package for scanning API controllers, sets the paths to include all paths,
     and provides additional API information through the apiInfo() method.

     @return The configured Docket bean.
     */
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("requirement_t7.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**

     Provides the API information for Swagger documentation.
     It sets the title, description, and version of the API.

     @return The configured ApiInfo object.
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Documentation")
                .description("API documentation for T7-G26")
                .version("1.0.0")
                .build();
    }

}
