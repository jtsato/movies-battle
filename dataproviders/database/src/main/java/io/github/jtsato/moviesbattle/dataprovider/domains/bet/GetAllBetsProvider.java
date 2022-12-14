package io.github.jtsato.moviesbattle.dataprovider.domains.bet;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.bet.xcutting.GetAllBetsGateway;
import io.github.jtsato.moviesbattle.dataprovider.common.ListMapper;
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
        final EntityGraph entityGraph = EntityGraphUtils.fromAttributePaths("quiz.game.player");
        final List<BetEntity> bets = betRepository.findAll(entityGraph);
        return listMapper.of(bets, betMapper::of);
    }
}
