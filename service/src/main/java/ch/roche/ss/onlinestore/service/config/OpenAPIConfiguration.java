package ch.roche.ss.onlinestore.service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info =
@Info(
        title = "Online Store",
        description = "Simple SpringBoot app simulating online store REST service",
        version = "v1",
        license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"),
        contact = @Contact(name = "Štefan Šimec", email = "stefan.simec@gmail.com")
)

)
public class OpenAPIConfiguration {
}
