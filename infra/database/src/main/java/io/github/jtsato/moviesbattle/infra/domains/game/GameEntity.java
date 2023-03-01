package io.github.jtsato.moviesbattle.infra.domains.game;

import io.github.jtsato.moviesbattle.infra.domains.player.PlayerEntity;
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
@Table(name = "GAMES", indexes = {
        @Index(columnList = "PLAYER_ID", name = "IDX_GAMES_PLAYER_ID"),
        @Index(columnList = "CREATED_AT", name = "IDX_GAMES_CREATED_AT"),
        @Index(columnList = "UPDATED_AT", name = "IDX_GAMES_UPDATED_AT"),
})
public class GameEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2781124783562798431L;

    @Access(AccessType.PROPERTY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAME_ID", updatable = false, insertable = false)
    private Long id;

    @JoinColumn(name = "PLAYER_ID", foreignKey = @ForeignKey(name = "FK_GAMES_PLAYER_ID"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PlayerEntity player;

    @Column(name = "STATUS_", nullable = false)
    private String status;

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
        final GameEntity other = (GameEntity) obj;
        if (id == null) {
            return other.id == null;
        } else
            return id.equals(other.id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Game [id=");
        builder.append(id);
        builder.append(", createdAt=");
        builder.append(createdAt);
        builder.append(", updatedAt=");
        builder.append(updatedAt);
        builder.append(", status=");
        builder.append(status);
        builder.append("]");
        return builder.toString();
    }
}
