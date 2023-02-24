package io.github.jtsato.moviesbattle.configuration;

import io.github.jtsato.moviesbattle.entrypoint.rest.common.WebRequest;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jorge Takeshi Sato
 */

@Profile("test")
@Configuration
public class WebRequestStubConfiguration {

    private static final List<ImmutablePair<String, String>> PLAYERS = Arrays.asList(
        new ImmutablePair<>("john.smith.zero@xyz.com", "John Smith"),
        new ImmutablePair<>("joe.doe.one@xyz.com", "Joe Doe"),
        new ImmutablePair<>("ashley.williams.two@xyz.com", "Ashley Williams")
    );

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public WebRequest getWebRequest() {
        return buildWebRequest(getHttpRequest());
    }

    public HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    private WebRequest buildWebRequest(HttpServletRequest request) {
        int playerId = NumberUtils.toInt(request.getHeader("PlayerId"));
        int index = playerId < 0 || playerId > PLAYERS.size() - 1 ? 0 : playerId;
        final ImmutablePair<String, String> player = PLAYERS.get(index);
        return new WebRequest(player.getLeft(), player.getRight(), request.getRequestURI());
    }
}
