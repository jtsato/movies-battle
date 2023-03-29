package io.github.jtsato.moviesbattle.infra.domains.movie;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jorge Takeshi Sato
 */

@Generated
public record MovieResponse (
        Long id,
        String imdbId,
        String title,
        String year,
        String genre,
        Float imdbRating,
        Long imdbVotes,
        Float score,
        String posterUrl)
        implements Serializable {

    @Serial
    private static final long serialVersionUID = -8366908225523005779L;
}
