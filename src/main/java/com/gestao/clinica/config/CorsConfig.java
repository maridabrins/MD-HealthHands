package com.gestao.clinica.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
        .allowedOrigins("https://gestao-clinicamedica-c2edatdwbcace3gw.brazilsouth-01.azurewebsites.net")
        .allowedMethods("*")
        .allowedHeaders("*")
        .allowCredentials(true);
    }
}