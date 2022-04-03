package vtb.spring;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class OperaApiConfig {
    private final String title = String.format("%s API", "Operas");
    private final String description = "Домашнее задание для проекта Оперы (Restы)";
//    private final String termOfService = "api/operas/api-docs";
    private final String version = "0.0.1";

    @Bean
    public OpenAPI customOpenAPI(){
        Map<String, Object> extensions = new LinkedHashMap<>();
        extensions.putIfAbsent("test", "AnyString");
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description)
//                        .termsOfService(termOfService)
                        .version(version)
                        .extensions(extensions)
            ) ;
    }
}
