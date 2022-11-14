package io.github.jtsato.moviesbattle.core.domains.quiz.model;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.game.models.Game;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jorge Takeshi Sato
 */

@Generated
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Quiz implements Serializable {

    @Serial
    private static final long serialVersionUID = 8231755197403971745L;

    private final Long id;
    @Setter private Game game;
    @Setter private Bet bet;
    private final String optionOneId;
    private final String optionOneTitle;
    private final String optionOneYear;
    private final String optionTwoId;
    private final String optionTwoTitle;
    private final String optionTwoYear;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
