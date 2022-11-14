package io.github.jtsato.moviesbattle.entrypoint.rest.domains.bet.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterBetRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -6188513601367036306L;

    private String optionId;
}
