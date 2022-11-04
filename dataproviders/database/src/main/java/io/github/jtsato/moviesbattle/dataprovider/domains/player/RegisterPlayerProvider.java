package io.github.jtsato.moviesbattle.dataprovider.domains.player;

import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerGateway;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jorge Takeshi Sato
 */

@Transactional
@Service
public class RegisterPlayerProvider implements RegisterPlayerGateway {

    private final PlayerMapper playerMapper = Mappers.getMapper(PlayerMapper.class);
    
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Player execute(final Player player) {
        final PlayerEntity playerEntity = playerMapper.of(player);
        return playerMapper.of(playerRepository.saveAndFlush(playerEntity));
    }
}
