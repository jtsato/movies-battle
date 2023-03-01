package io.github.jtsato.moviesbattle.infra.domains.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PLAYERS",
       uniqueConstraints = {
            @UniqueConstraint(columnNames = {"EMAIL"}, name = "UN_PLAYERS_EMAIL")
       },
       indexes = {
            @Index(columnList = "CREATED_AT", name = "IDX_PLAYERS_CREATED_AT"),
            @Index(columnList = "UPDATED_AT", name = "IDX_PLAYERS_UPDATED_AT"),
       }
)
public class PlayerEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4763834594539647861L;
    
    @Access(AccessType.PROPERTY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID", updatable = false, insertable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

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
        final PlayerEntity other = (PlayerEntity) obj;
        if (id == null) {
            return other.id == null;
        }
        return id.equals(other.id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Player [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", email=");
        builder.append(email);
        builder.append(", createdAt=");
        builder.append(createdAt);
        builder.append(", updatedAt=");
        builder.append(updatedAt);
        builder.append("]");
        return builder.toString();
    }
}
