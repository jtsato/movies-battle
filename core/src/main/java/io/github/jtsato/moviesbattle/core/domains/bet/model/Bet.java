package io.github.jtsato.moviesbattle.core.domains.bet.model;

import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Bet implements Serializable {

    private static final long serialVersionUID = 2369191175204185991L;

    private final Long id;
    @Setter private Quiz quiz;
    private final String optionId;
    private final Boolean winTheBet;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
