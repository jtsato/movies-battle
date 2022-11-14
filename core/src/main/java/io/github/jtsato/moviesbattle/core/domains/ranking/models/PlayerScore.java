package io.github.jtsato.moviesbattle.core.domains.ranking.models;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jorge Takeshi Sato
 */

@Generated
public record PlayerScore(String playerEmail, String playerName, Float score) implements Serializable {

    @Serial
    private static final long serialVersionUID = 8231755197403971745L;
}
