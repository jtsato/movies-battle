package io.github.jtsato.moviesbattle.entrypoint.rest.domains.bet;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@AllArgsConstructor
public class BetResponse implements Serializable {

    private static final long serialVersionUID = 1698092149790294590L;

    private final Long id;
    private final String status;
    private final Boolean winTheBet;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
