package io.github.jtsato.moviesbattle.core.domains.ranking.usecase;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.bet.xcutting.GetAllBetsGateway;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerUseCase;
import io.github.jtsato.moviesbattle.core.domains.player.xcutting.GetPlayerByEmailGateway;
import io.github.jtsato.moviesbattle.core.domains.ranking.models.PlayerScore;
import io.github.jtsato.moviesbattle.core.domains.ranking.models.Ranking;
import lombok.*;

import jakarta.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * A Use Case follows these steps:
 * - Takes input
 * - Validates business rules
 * - Manipulates the model state
 * - Returns output
 */

/**
 * @author Jorge Takeshi Sato
 */

@Named
@RequiredArgsConstructor
public class GetGeneralRankingUseCaseImpl implements GetGeneralRankingUseCase {

    private final GetPlayerByEmailGateway getPlayerByEmailGateway;
    private final RegisterPlayerUseCase registerPlayerUseCase;
    private final GetAllBetsGateway getAllBetsGateway;

    @Override
    public Ranking execute() {

        final List<Bet> bets = getAllBetsGateway.execute();
        final Map<Player, Totalizer> playersTotalizer = buildPlayersTotalizer(bets);

        final Ranking ranking = new Ranking();
        playersTotalizer.forEach((player, totalizer) -> {
            float score = totalizer.getNumberOfQuizzes() * ((float) totalizer.getNumberOfWins() / (float) totalizer.getNumberOfQuizzes());
            final PlayerScore playerScore = new PlayerScore(player.email(), player.name(), score);
            ranking.getPlayersScores().add(playerScore);
        });

        return ranking;
    }

    private static Map<Player, Totalizer> buildPlayersTotalizer(List<Bet> bets) {

        final Map<Player, Totalizer> playersTotalizer = new HashMap<>();

        bets.forEach(bet -> {
            final Player player = bet.quiz().game().player();
            int count = bet.winTheBet() ? 1 : 0;
            final Totalizer totalizer = playersTotalizer.get(player);
            if (totalizer == null) {
                playersTotalizer.put(player, new Totalizer(1, count));
            } else {
                totalizer.setNumberOfQuizzes(totalizer.getNumberOfQuizzes() + 1);
                totalizer.setNumberOfWins(totalizer.getNumberOfWins() + count);
            }
        });

        return playersTotalizer;
    }
}

@Generated
@Setter
@Getter
@AllArgsConstructor
class Totalizer {
    private int numberOfQuizzes;
    private int numberOfWins;
}
