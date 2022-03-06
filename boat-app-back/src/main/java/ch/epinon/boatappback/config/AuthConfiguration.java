package ch.epinon.boatappback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        //or any domain that you want to restrict to
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        //Add the method support as you like
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
