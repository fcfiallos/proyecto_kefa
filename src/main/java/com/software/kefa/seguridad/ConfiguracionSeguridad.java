package com.software.kefa.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This class provides the configuration for security in the application.
 */
@Configuration
public class ConfiguracionSeguridad {

    /**
     * Returns a BCryptPasswordEncoder object.
     * 
     * @return the BCryptPasswordEncoder object.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
