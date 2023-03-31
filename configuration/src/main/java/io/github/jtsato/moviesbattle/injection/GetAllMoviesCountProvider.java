package io.github.jtsato.moviesbattle.injection;

import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetAllMoviesCountGateway;
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
public class GetAllMoviesCountProvider implements GetAllMoviesCountGateway {

    @Value("${app.gateway.get-all-movies-count.provider-classname}")
    private String className;

    private final ApplicationContext context;

    @Override
    public Long execute() {
        final GetAllMoviesCountGateway provider = findProvider(className);
        return provider.execute();
    }

    private GetAllMoviesCountGateway findProvider(final String providerClassname) {
        final String[] beanNames = context.getBeanNamesForType(GetAllMoviesCountGateway.class);
        final String beanName = Arrays.stream(beanNames)
                .filter(name -> name.equalsIgnoreCase(providerClassname))
                .findFirst()
                .orElseThrow(() -> throwIllegalArgumentException(providerClassname));
        final GetAllMoviesCountGateway provider = (GetAllMoviesCountGateway) context.getBean(beanName);
        return Optional.of(provider).orElseThrow(() -> throwIllegalArgumentException(providerClassname));
    }

    private static IllegalArgumentException throwIllegalArgumentException(final String providerClassname) {
        final String gatewayClassname = GetAllMoviesCountGateway.class.getSimpleName();
        final String message = String.format("Provider %s not found to implement the gateway %s", providerClassname, gatewayClassname);
        throw new IllegalArgumentException(message);
    }
}
