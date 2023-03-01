package io.github.jtsato.moviesbattle.infra.domains.movie;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOVIES",
       indexes = {
            @Index(columnList = "TITLE", name = "IDX_MOVIES_TITLE"),
            @Index(columnList = "YEAR_", name = "IDX_MOVIES_YEAR"),
            @Index(columnList = "GENRE", name = "IDX_MOVIES_GENRE"),
       }
)
public class MovieEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2997286413501054881L;
    
    @Access(AccessType.PROPERTY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID", updatable = false, insertable = false)
    private Long id;

    @Column(name = "IMDB_ID", nullable = false)
    private String imdbId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "YEAR_", nullable = false)
    private String year;

    @Column(name = "GENRE", nullable = false)
    private String genre;

    @Column(name = "IMDB_RATING", nullable = false)
    private Float imdbRating;

    @Column(name = "IMDB_VOTES", nullable = false)
    private Long imdbVotes;

    @Transient
    public Float getScore() {
        return imdbRating * imdbVotes;
    }

    @Column(name = "POSTER_URL", nullable = false)
    private String posterUrl;

    @Override
    public int hashCode() {
        final int prime = 23;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MovieEntity other = (MovieEntity) obj;
        if (id == null) {
            return other.id == null;
        }
        return id.equals(other.id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Movie [id=");
        builder.append(id);
        builder.append(", ImdbId=");
        builder.append(imdbId);
        builder.append(", title=");
        builder.append(title);
        builder.append(", year=");
        builder.append(year);
        builder.append(", genre=");
        builder.append(genre);
        builder.append(", imdbRating=");
        builder.append(imdbRating);
        builder.append(", imdbVotes=");
        builder.append(imdbVotes);
        builder.append(", posterUrl=");
        builder.append(posterUrl);
        builder.append("]");
        return builder.toString();
    }
}
