package io.github.jtsato.moviesbattle.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Jorge Takeshi Sato
 */

@Profile({"test", "dev"})
@EnableWebSecurity
public class ByPassSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity httpSecurity)
        throws Exception {
        httpSecurity.csrf().disable().authorizeRequests().antMatchers("/**").permitAll().anyRequest().anonymous();
        httpSecurity.headers().frameOptions().sameOrigin();
    }
}
