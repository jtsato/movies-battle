package io.github.jtsato.moviesbattle.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Jorge Takeshi Sato
 */

@Configuration
public class MessageSourceConfiguration {

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource rrbMessageSource = new ReloadableResourceBundleMessageSource();
        rrbMessageSource.setBasename("classpath:messages");
        rrbMessageSource.setDefaultEncoding("UTF-8");
        return rrbMessageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        final LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }
}
