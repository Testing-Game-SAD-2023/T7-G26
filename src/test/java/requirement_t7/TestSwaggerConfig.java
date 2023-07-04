package requirement_t7;

import org.junit.jupiter.api.BeforeEach;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

@EnableSwagger2
public class TestSwaggerConfig {
    private SwaggerConfig swaggerConfig;

    @BeforeEach
    public void setup() {
        swaggerConfig = new SwaggerConfig();
    }

    @Test
    public void testApiDocket_ReturnsDocketInstance() {
        // Act
        Docket docket = swaggerConfig.apiDocket();

        // Assert
        assertEquals(Docket.class, docket.getClass());
    }

    @Test
    public void testApiInfo_ReturnsExpectedApiInfo() {
        // Act
        ApiInfo apiInfo = swaggerConfig.apiInfo();

        // Assert
        assertEquals("API Documentation", apiInfo.getTitle());
        assertEquals("API documentation for T7-G26", apiInfo.getDescription());
        assertEquals("1.0.0", apiInfo.getVersion());
    }
}
