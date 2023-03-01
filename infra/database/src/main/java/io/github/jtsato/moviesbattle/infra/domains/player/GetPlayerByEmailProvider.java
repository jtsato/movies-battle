package io.github.jtsato.moviesbattle.infra.domains.player;

import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.xcutting.GetPlayerByEmailGateway;
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
public class GetPlayerByEmailProvider implements GetPlayerByEmailGateway {

    private final PlayerMapper playerMapper = Mappers.getMapper(PlayerMapper.class);
    
    private final PlayerRepository playerRepository;

    @Override
    public Optional<Player> execute(String email) {
        final Optional<PlayerEntity> optional = playerRepository.findByEmailIgnoreCase(email);
        return optional.map(playerMapper::of);
    }
}
