package io.github.jtsato.moviesbattle.dataprovider.domains.game;

import io.github.jtsato.moviesbattle.core.domains.game.models.Game;
import io.github.jtsato.moviesbattle.core.domains.game.usecases.start.RegisterGameGateway;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jorge Takeshi Sato
 */

@Transactional
@Service
public class RegisterGameProvider implements RegisterGameGateway {

    private final GameMapper gameMapper = Mappers.getMapper(GameMapper.class);
    
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game execute(final Game game) {
        final GameEntity gameEntityToPersist = gameMapper.of(game);
        final GameEntity gameEntity = gameRepository.save(gameEntityToPersist);
        return gameMapper.of(gameEntity);
    }
}
