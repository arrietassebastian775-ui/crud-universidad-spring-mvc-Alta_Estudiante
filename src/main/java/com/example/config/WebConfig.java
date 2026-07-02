package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Services.FacultadService;
import com.example.entities.Facultad;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final FacultadService facultadService;

    @Bean
    public Converter<String, Facultad> facultadConverter() {
        return new Converter<String, Facultad>() {
            @Override
            public Facultad convert(String source) {
                if (source == null || source.isBlank()) {
                    return null;
                }
                int id = Integer.parseInt(source);
                return facultadService.getAllFacultades().stream()
                        .filter(f -> f.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
        };
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(facultadConverter());
    }

    @Override
    public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imagenes/**")
                .addResourceLocations("file:uploads/");
    }

}
