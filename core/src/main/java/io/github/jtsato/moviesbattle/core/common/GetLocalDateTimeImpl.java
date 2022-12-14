package io.github.jtsato.moviesbattle.core.common;

import java.time.LocalDateTime;

import javax.inject.Named;

/**
 * @author Jorge Takeshi Sato
 */

@Named
public class GetLocalDateTimeImpl implements GetLocalDateTime {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
