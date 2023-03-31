package io.github.jtsato.moviesbattle.injection;

import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetMovieByImdbIdGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@Primary
@RequiredArgsConstructor
@Service
public class GetMovieByImdbIdProvider implements GetMovieByImdbIdGateway {

    @Value("${app.gateway.get-movie-by-imdb-id.provider-classname}")
    private String providerClassname;

    private final ApplicationContext context;

    @Override
    public Optional<Movie> execute(final String imdbId) {
        final GetMovieByImdbIdGateway provider = findProvider(providerClassname);
        return provider.execute(imdbId);
    }

    private GetMovieByImdbIdGateway findProvider(final String providerClassname) {
        final String[] beanNames = context.getBeanNamesForType(GetMovieByImdbIdGateway.class);
        final String beanName = Arrays.stream(beanNames)
                .filter(name -> name.equalsIgnoreCase(providerClassname))
                .findFirst()
                .orElseThrow(() -> throwIllegalArgumentException(providerClassname));
        final GetMovieByImdbIdGateway provider = (GetMovieByImdbIdGateway) context.getBean(beanName);
        return Optional.of(provider).orElseThrow(() -> throwIllegalArgumentException(providerClassname));
    }

    private static IllegalArgumentException throwIllegalArgumentException(final String providerClassname) {
        final String gatewayClassname = GetMovieByImdbIdGateway.class.getSimpleName();
        final String message = String.format("Provider %s not found to implement the gateway %s", providerClassname, gatewayClassname);
        throw new IllegalArgumentException(message);
    }
}
