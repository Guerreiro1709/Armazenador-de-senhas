package com.example.passwordManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Definindo rotas públicas, qualquer pessoas pode acessar essas rotas
     * */
    private final String[] PUBLIC_ROUTES = {"/auth/**", "/css/**", "/js/**", "/assets/**", "/img/**", "/error/**" };


    /**
     *  Retorna uma senha encriptografada.
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Filtro de segurança, ele dá acesso aos sites, requestMatcher faz necesário uma regra para poder
     * acessar certas rotas.
     *
     * Também define a página de login
     * */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(PUBLIC_ROUTES)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .defaultSuccessUrl("/listar");

        return http.build();
    }
}