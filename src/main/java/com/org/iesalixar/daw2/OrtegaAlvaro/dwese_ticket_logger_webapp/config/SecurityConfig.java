package com.org.iesalixar.daw2.OrtegaAlvaro.dwese_ticket_logger_webapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configura la seguridad de la aplicación, definiendo autenticación y autorización
 * para diferentes roles de usuario, y gestionando la política de sesiones.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * Configura el filtro de seguridad para las solicitudes HTTP.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Entrando en el método securityFilterChain");

        http
                .authorizeHttpRequests(auth -> {
                    logger.debug("Configurando autorización de solicitudes HTTP");
                    auth
                            .requestMatchers("/", "/hello").permitAll() // Acceso anónimo
                            .requestMatchers("/admin").hasRole("ADMIN") // Solo ADMIN
                            .requestMatchers("/regions", "/provinces", "/supermarkets",
                                    "/locations", "/categories").hasRole("MANAGER") // Solo MANAGER
                            .requestMatchers("/tickets").hasRole("USER") // Solo USER
                            .anyRequest().authenticated(); // Cualquier otra solicitud requiere autenticación
                })
                .formLogin(form -> {
                    logger.debug("Configurando formulario de inicio de sesión");
                    form
                            .loginPage("/login")
                            .defaultSuccessUrl("/")
                            .permitAll();
                })
                .sessionManagement(session -> {
                    logger.debug("Configurando política de gestión de sesiones");
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                });

        logger.info("Saliendo del método securityFilterChain");
        return http.build();
    }

    /**
     * Configura usuarios en memoria con diferentes roles.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        logger.info("Entrando en el método userDetailsService");

        logger.debug("Creando usuario con rol USER");
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        logger.debug("Creando usuario con rol ADMIN");
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        logger.debug("Creando usuario con rol MANAGER");
        UserDetails manager = User.builder()
                .username("manager")
                .password(passwordEncoder().encode("password"))
                .roles("MANAGER")
                .build();

        logger.info("Saliendo del método userDetailsService");
        return new InMemoryUserDetailsManager(user, admin, manager);
    }

    /**
     * Codificador de contraseñas BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Entrando en el método passwordEncoder");
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        logger.info("Saliendo del método passwordEncoder");
        return encoder;
    }
}
