package io.github.jtsato.moviesbattle.core.domains.bet.usecase;

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
public class RegisterBetCommand extends SelfValidating<RegisterBetCommand> implements Serializable {

    @Serial
    private static final long serialVersionUID = -5702749756671761937L;

    @NotBlank(message = "validation.player.email.blank")
    private final String playerEmail;

    @NotBlank(message = "validation.player.name.blank")
    private final String playerName;

    @NotBlank(message = "validation.bet.option.blank")
    private final String optionId;

    public RegisterBetCommand(final String playerEmail, final String playerName, final String optionId) {
        this.playerEmail = playerEmail;
        this.playerName = playerName;
        this.optionId = optionId;
        this.validateSelf();
    }
}
