package com.mindhub.homebanking.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    // DAMOS LOS PERMISOS PARA ACCESO DESDE OTRA API/SERVIDOR
    // PRIMERO LA LISTA DESDE DONDE PODEMOS RECIBIR PETICIONES, LUEGO LOS MÉTODOS CON LOS QUE PUEDEN LLEGAR LAS PETICIONES
    // /** => A LAS PETICIONES DE LOS CONTROLADORES Y VIENE DE LOS SERVIDORES PERMITIDOS, VAN A ACCEDER


    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:3000", "http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}






