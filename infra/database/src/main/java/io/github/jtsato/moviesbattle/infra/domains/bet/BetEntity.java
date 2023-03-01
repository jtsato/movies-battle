package io.github.jtsato.moviesbattle.infra.domains.bet;

import io.github.jtsato.moviesbattle.infra.domains.quiz.QuizEntity;
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
@Table(name = "BETS",
        indexes = {
                @Index(columnList = "BET_ID", name = "IDX_BETS_BET_ID"),
                @Index(columnList = "CREATED_AT", name = "IDX_BETS_CREATED_AT"),
                @Index(columnList = "UPDATED_AT", name = "IDX_BETS_UPDATED_AT"),
        }
)
public class BetEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8305357111222422457L;

    @Access(AccessType.PROPERTY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BET_ID", updatable = false, insertable = false)
    private Long id;

    @JoinColumn(name = "QUIZ_ID", foreignKey = @ForeignKey(name = "FK_BETS_QUIZ_ID"))
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private QuizEntity quiz;

    @Column(name = "OPTION_ID", nullable = false)
    private String optionId;

    @Column(name = "WIN_THE_BET", nullable = false)
    private Boolean winTheBet;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
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
        final BetEntity other = (BetEntity) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bet [id=");
        builder.append(id);
        builder.append(", quiz=");
        builder.append(quiz);
        builder.append(", optionId=");
        builder.append(optionId);
        builder.append(", winTheBet=");
        builder.append(winTheBet);
        builder.append(", createdAt=");
        builder.append(createdAt);
        builder.append(", updatedAt=");
        builder.append(updatedAt);
        builder.append("]");
        return builder.toString();
    }
}
