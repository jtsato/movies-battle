package io.github.jtsato.moviesbattle.configuration;

import io.github.jtsato.moviesbattle.entrypoint.rest.common.WebRequest;
import org.springframework.context.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Jorge Takeshi Sato
 */

@Profile("!test")
@Configuration
public class WebRequestBuilderConfiguration {

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public WebRequest getWebRequest() {
        return buildWebRequest(getRequest());
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    private WebRequest buildWebRequest(final HttpServletRequest request) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication instanceof AnonymousAuthenticationToken)) {
            throw new AccessDeniedException("Access is denied");
        }

        final OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        final OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();
        final Map<String, Object> attributes = oAuth2User.getAttributes();
        final String email = (String) attributes.get("email");
        final String name = (String) attributes.get("name");
        return new WebRequest(email, name, request.getRequestURI());
    }
}
