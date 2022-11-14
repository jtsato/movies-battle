package io.github.jtsato.moviesbattle.entrypoint.rest.domains.quiz;

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
public class QuizResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 9217004935831572247L;

    private final Long id;
    private final String optionOneId;
    private final String optionOneTitle;
    private final String optionOneYear;
    private final String optionTwoId;
    private final String optionTwoTitle;
    private final String optionTwoYear;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}

