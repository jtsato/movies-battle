package io.github.jtsato.moviesbattle.core.domains.player.usecase;

import io.github.jtsato.moviesbattle.core.common.GetLocalDateTime;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import jakarta.inject.Named;
import java.time.LocalDateTime;

/*
 * A Use Case follows these steps:
 * - Takes input
 * - Validates business rules
 * - Manipulates the model state
 * - Returns output
 */

/**
 * @author Jorge Takeshi Sato
 */

@Named
@RequiredArgsConstructor
public class RegisterPlayerUseCaseImpl implements RegisterPlayerUseCase {

    private final RegisterPlayerGateway registerPlayerGateway;
    private final GetLocalDateTime getLocalDateTime;

    @Override
    public Player execute(final RegisterPlayerCommand parameters) {

        final String name = StringUtils.stripToEmpty(parameters.getName());
        final String email = StringUtils.stripToEmpty(parameters.getEmail());
        final LocalDateTime createdAt = getLocalDateTime.now();
        final LocalDateTime updatedAt = getLocalDateTime.now();

        final Player player = new Player(null, name, email, createdAt, updatedAt);

        return registerPlayerGateway.execute(player);
    }
}
