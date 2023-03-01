package io.github.jtsato.moviesbattle.infra.domains.game;

import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.usecase.start.RegisterGameGateway;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Transactional
@Service
public class RegisterGameProvider implements RegisterGameGateway {

    private final GameMapper gameMapper = Mappers.getMapper(GameMapper.class);
    
    private final GameRepository gameRepository;

    @Override
    public Game execute(final Game game) {
        final GameEntity gameEntityToPersist = gameMapper.of(game);
        final GameEntity gameEntity = gameRepository.save(gameEntityToPersist);
        return gameMapper.of(gameEntity);
    }
}
