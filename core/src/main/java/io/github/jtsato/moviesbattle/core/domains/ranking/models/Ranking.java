package io.github.jtsato.moviesbattle.core.domains.ranking.models;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Takeshi Sato
 */

@Generated
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Ranking implements Serializable {

    @Serial
    private static final long serialVersionUID = -1097163842546426788L;

    private final List<PlayerScore> playersScores = new ArrayList<>();
}
