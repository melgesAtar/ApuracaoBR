package br.com.atardigital.apuracaoBr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Libera todas as rotas
                .allowedOriginPatterns("*") // Permite todas as origens com suporte a credenciais
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Libera métodos HTTP
                .allowedHeaders("*") // Permite todos os headers
                .allowCredentials(true); // Permite envio de credenciais
    }
}