package io.github.jtsato.moviesbattle.infra.domains.player;

import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import org.mapstruct.Mapper;

/**
 * @author Jorge Takeshi Sato
 */

@Mapper
public interface PlayerMapper {
    Player of(final PlayerEntity playerEntity);
    PlayerEntity of(final Player player);
}
