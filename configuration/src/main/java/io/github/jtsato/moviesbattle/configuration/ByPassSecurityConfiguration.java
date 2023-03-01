package io.github.jtsato.moviesbattle.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Jorge Takeshi Sato
 */

@Profile({"test", "dev"})
@EnableWebSecurity
@Configuration
public class ByPassSecurityConfiguration  {

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity)
        throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/**")
                .permitAll()
                .anyRequest()
                .anonymous();

        httpSecurity
                .headers()
                .frameOptions()
                .sameOrigin();

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**");
    }
}
