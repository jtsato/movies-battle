package io.github.jtsato.moviesbattle.dataprovider.domains.game;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.model.Status;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetGameByPlayerIdAndStatusGateway;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetGameByPlayerIdAndStatusProvider implements GetGameByPlayerIdAndStatusGateway {

    private final GameMapper gameMapper = Mappers.getMapper(GameMapper.class);
    
    private final GameRepository gameRepository;

    @Override
    public Optional<Game> execute(Long playerId, Status status) {
        final EntityGraph entityGraph = EntityGraphUtils.fromAttributePaths("player");
        final Optional<GameEntity> optional = gameRepository.findByStatusIgnoreCaseAndPlayerId(status.name(), playerId, entityGraph);
        return optional.map(gameMapper::of);
    }
}
