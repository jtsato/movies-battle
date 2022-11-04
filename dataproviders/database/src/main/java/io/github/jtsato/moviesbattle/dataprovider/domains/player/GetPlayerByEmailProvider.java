package io.github.jtsato.moviesbattle.dataprovider.domains.player;

import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.xcutting.GetPlayerByEmailGateway;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@Transactional(readOnly = true)
@Service
public class GetPlayerByEmailProvider implements GetPlayerByEmailGateway {

    private final PlayerMapper playerMapper = Mappers.getMapper(PlayerMapper.class);
    
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Optional<Player> execute(String email) {
        final Optional<PlayerEntity> optional = playerRepository.findByEmailIgnoreCase(email);
        return optional.map(playerMapper::of);
    }
}
