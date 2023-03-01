package io.github.jtsato.moviesbattle.infra.domains.quiz;

import io.github.jtsato.moviesbattle.infra.domains.bet.BetEntity;
import io.github.jtsato.moviesbattle.infra.domains.game.GameEntity;
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
@Table(name = "QUIZZES",
       indexes = {
            @Index(columnList = "GAME_ID", name = "IDX_QUIZZES_GAME_ID"),
            @Index(columnList = "CREATED_AT", name = "IDX_QUIZZES_CREATED_AT"),
            @Index(columnList = "UPDATED_AT", name = "IDX_QUIZZES_UPDATED_AT"),
       }
)
public class QuizEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8305357111222422457L;
    
    @Access(AccessType.PROPERTY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUIZ_ID", updatable = false, insertable = false)
    private Long id;

    @JoinColumn(name = "GAME_ID", foreignKey = @ForeignKey(name = "FK_QUIZZES_GAME_ID"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GameEntity game;

    @JoinColumn(name = "BET_ID", foreignKey = @ForeignKey(name = "FK_QUIZZES_QUIZ_ID"))
    @OneToOne(fetch = FetchType.LAZY)
    private BetEntity bet;

    @Column(name = "OPTION_ONE_ID", nullable = false)
    private String optionOneId;

    @Column(name = "OPTION_ONE_TITLE", nullable = false)
    private String optionOneTitle;

    @Column(name = "OPTION_ONE_YEAR", nullable = false)
    private String optionOneYear;

    @Column(name = "OPTION_TWO_ID", nullable = false)
    private String optionTwoId;

    @Column(name = "OPTION_TWO_TITLE", nullable = false)
    private String optionTwoTitle;

    @Column(name = "OPTION_TWO_YEAR", nullable = false)
    private String optionTwoYear;

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
        final QuizEntity other = (QuizEntity) obj;
        if (id == null) {
            return other.id == null;
        }
        return id.equals(other.id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Quiz [id=");
        builder.append(id);
        builder.append(", optionOneId=");
        builder.append(optionOneId);
        builder.append(", optionOneTitle=");
        builder.append(optionOneTitle);
        builder.append(", optionOneYear=");
        builder.append(optionOneYear);
        builder.append(", optionTwoId=");
        builder.append(optionTwoId);
        builder.append(", optionTwoTitle=");
        builder.append(optionTwoTitle);
        builder.append(", optionTwoYear=");
        builder.append(optionTwoYear);
        builder.append(", createdAt=");
        builder.append(createdAt);
        builder.append(", updatedAt=");
        builder.append(updatedAt);
        builder.append("]");
        return builder.toString();
    }
}
