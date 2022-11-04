package io.github.jtsato.moviesbattle.core.common.paging;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class PageImpl<T> implements Page<T> {

    private final List<T> content;
    private final Pageable pageable;
}
