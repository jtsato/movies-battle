package io.github.jtsato.moviesbattle.entrypoint.rest.domains.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@AllArgsConstructor
public class PlayerResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -2882249634089711164L;

    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
