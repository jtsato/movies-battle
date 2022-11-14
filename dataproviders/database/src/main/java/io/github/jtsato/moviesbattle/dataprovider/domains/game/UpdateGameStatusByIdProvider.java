package io.github.jtsato.moviesbattle.dataprovider.domains.game;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import io.github.jtsato.moviesbattle.core.domains.game.models.Game;
import io.github.jtsato.moviesbattle.core.domains.game.usecases.end.UpdateGameStatusByIdGateway;
import io.github.jtsato.moviesbattle.dataprovider.domains.player.PlayerRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@Transactional
@Service
public class UpdateGameStatusByIdProvider implements UpdateGameStatusByIdGateway {

    private final GameMapper gameMapper = Mappers.getMapper(GameMapper.class);

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Optional<Game> execute(final Game game) {

        final EntityGraph entityGraph = EntityGraphUtils.fromAttributePaths("player");
        final Optional<GameEntity> optional = gameRepository.findById(game.id(), entityGraph);

        return optional.map(gameEntity -> updateGameEntity(gameEntity, game));
    }

    private Game updateGameEntity(final GameEntity gameEntity, final Game game) {
        gameEntity.setUpdatedAt(game.updatedAt());
        gameEntity.setStatus(game.status().name());
        return gameMapper.of(gameRepository.saveAndFlush(gameEntity));
    }
}
