package io.github.jtsato.moviesbattle.entrypoint.rest.common;

import lombok.*;

import java.io.Serializable;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class WebRequest implements Serializable {

    private static final long serialVersionUID = 1186467498211971036L;

    protected String email;
    protected String fullName;
    protected String path;
}
