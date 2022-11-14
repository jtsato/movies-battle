package io.github.jtsato.moviesbattle.entrypoint.rest.domains.bet;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jorge Takeshi Sato
 */

public record BetResponse(
        Long id,
        String status,
        Boolean winTheBet,
        LocalDateTime createdAt,
        LocalDateTime updatedAt)
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 1698092149790294590L;
}
