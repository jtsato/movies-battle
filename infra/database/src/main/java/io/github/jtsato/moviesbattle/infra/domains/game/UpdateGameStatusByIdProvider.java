package io.github.jtsato.moviesbattle.infra.domains.game;

import com.cosium.spring.data.jpa.entity.graph.domain2.DynamicEntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.usecase.end.UpdateGameStatusByIdGateway;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateGameStatusByIdProvider implements UpdateGameStatusByIdGateway {

    private final GameMapper gameMapper = Mappers.getMapper(GameMapper.class);

    private final GameRepository gameRepository;

    @Override
    public Optional<Game> execute(final Game game) {

        final EntityGraph entityGraph = DynamicEntityGraph.loading().addPath("player").build();
        final Optional<GameEntity> optional = gameRepository.findById(game.id(), entityGraph);

        return optional.map(gameEntity -> updateGameEntity(gameEntity, game));
    }

    private Game updateGameEntity(final GameEntity gameEntity, final Game game) {
        gameEntity.setUpdatedAt(game.updatedAt());
        gameEntity.setStatus(game.status().name());
        return gameMapper.of(gameRepository.saveAndFlush(gameEntity));
    }
}
