package com.nimagu.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BackApplication.class);
        
        // Registrar el inicializador que carga el archivo .env antes de levantar el contexto
        application.addInitializers(new DotenvInitializer());
        
        application.run(args);
    }

    // Inicializador personalizado para Spring
    public static class DotenvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
            Map<String, Object> envProperties = new HashMap<>();
            
            // Copiar las variables directamente al entorno de Spring
            dotenv.entries().forEach(e -> envProperties.put(e.getKey(), e.getValue()));
            
            applicationContext.getEnvironment().getPropertySources()
                .addFirst(new MapPropertySource("dotenvProperties", envProperties));
        }
    }
}

