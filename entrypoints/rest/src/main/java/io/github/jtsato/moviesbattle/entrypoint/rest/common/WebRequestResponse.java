package io.github.jtsato.moviesbattle.entrypoint.rest.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@Setter
@ToString
public class WebRequestResponse extends WebRequest {

    private static final long serialVersionUID = -1468170822000362636L;

    public WebRequestResponse() {
        super();
        email = StringUtils.EMPTY;
        fullName = StringUtils.EMPTY;
    }

    public WebRequestResponse(final WebRequest webRequest) {
        super();
        email = webRequest.getEmail();
        fullName = webRequest.getFullName();
    }
}
