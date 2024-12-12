package ru.stenyaeva.back.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Позволяет CORS для всех эндпоинтов
//                .allowedOrigins("http://localhost:5173") // Разрешите источник (например, ваш фронтенд)
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешенные методы
//                .allowedHeaders("*") // Разрешенные заголовки
//                .allowCredentials(true); // Разрешите учетные данные, если нужно
//    }
//}
