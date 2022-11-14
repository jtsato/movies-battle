package io.github.jtsato.moviesbattle.core.domains.movie.model;

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
public class Movie implements Serializable {

    @Serial
    private static final long serialVersionUID = -8366908225523005779L;

    private final Long id;
    private final String imdbId;
    private final String title;
    private final String year;
    private final String genre;
    private final Float imdbRating;
    private final Long imdbVotes;
    private final Float score;
    private final String posterUrl;
}
