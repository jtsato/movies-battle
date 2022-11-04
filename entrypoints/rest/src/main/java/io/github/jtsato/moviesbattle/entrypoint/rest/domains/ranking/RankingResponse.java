package io.github.jtsato.moviesbattle.entrypoint.rest.domains.ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@AllArgsConstructor
public class RankingResponse implements Serializable {

    private static final long serialVersionUID = 329774416403185866L;

    private final List<PlayerScoreResponse> scores;
}
