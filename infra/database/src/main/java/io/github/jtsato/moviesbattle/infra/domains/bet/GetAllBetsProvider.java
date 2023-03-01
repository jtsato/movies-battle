package io.github.jtsato.moviesbattle.infra.domains.bet;

import com.cosium.spring.data.jpa.entity.graph.domain2.DynamicEntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.bet.xcutting.GetAllBetsGateway;
import io.github.jtsato.moviesbattle.infra.common.ListMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Transactional
@Service
public class GetAllBetsProvider implements GetAllBetsGateway {

    private final ListMapper<Bet, BetEntity> listMapper = new ListMapper<>() { };
    private final BetMapper betMapper = Mappers.getMapper(BetMapper.class);

    private final BetRepository betRepository;

    @Override
    public List<Bet> execute() {
        final EntityGraph entityGraph = DynamicEntityGraph.loading().addPath("quiz", "game", "player").build();
        final List<BetEntity> bets = betRepository.findAll(entityGraph);

        return listMapper.of(bets, betMapper::of);
    }
}
