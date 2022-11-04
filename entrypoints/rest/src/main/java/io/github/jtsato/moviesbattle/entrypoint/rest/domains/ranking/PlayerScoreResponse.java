package io.github.jtsato.moviesbattle.entrypoint.rest.domains.ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@AllArgsConstructor
public class PlayerScoreResponse implements Serializable {

    private static final long serialVersionUID = 329774416403185866L;

    private final String playerEmail;
    private final String playerName;
    private final Float score;
}
