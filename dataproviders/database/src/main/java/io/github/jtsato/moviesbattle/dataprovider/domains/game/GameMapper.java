package io.github.jtsato.moviesbattle.dataprovider.domains.game;

import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.dataprovider.domains.player.PlayerMapper;
import org.mapstruct.Mapper;

/**
 * @author Jorge Takeshi Sato
 */

@Mapper(uses = {PlayerMapper.class})
public interface GameMapper {

    Game of(final GameEntity gameEntity);
    GameEntity of(final Game game);
}
