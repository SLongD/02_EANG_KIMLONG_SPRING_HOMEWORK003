package org.kps.exceptionhadling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Event Ticketing System",
        version = "3.0",
        description = "Efficiently manage events with our streamlined ticketing system."
) )
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
