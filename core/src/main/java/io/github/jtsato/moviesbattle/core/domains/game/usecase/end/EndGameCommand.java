package io.github.jtsato.moviesbattle.core.domains.game.usecase.end;

import io.github.jtsato.moviesbattle.core.common.validation.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@ToString
public class EndGameCommand extends SelfValidating<EndGameCommand> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6754437157027756468L;

    @NotBlank(message = "validation.player.email.blank")
    private final String playerEmail;

    @NotBlank(message = "validation.player.name.blank")
    private final String playerName;

    public EndGameCommand(final String playerEmail, final String playerName) {
        this.playerEmail = playerEmail;
        this.playerName = playerName;
        this.validateSelf();
    }
}
