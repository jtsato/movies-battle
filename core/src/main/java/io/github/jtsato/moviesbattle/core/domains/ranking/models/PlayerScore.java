package io.github.jtsato.moviesbattle.core.domains.ranking.models;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jorge Takeshi Sato
 */

@Generated
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class PlayerScore implements Serializable {

    @Serial
    private static final long serialVersionUID = 8231755197403971745L;

    private final String playerEmail;
    private final String playerName;
    private final Float score;
}
