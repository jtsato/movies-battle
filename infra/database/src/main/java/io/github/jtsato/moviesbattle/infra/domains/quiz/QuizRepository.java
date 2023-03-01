package io.github.jtsato.moviesbattle.infra.domains.quiz;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@Repository
public interface QuizRepository extends EntityGraphJpaRepository<QuizEntity, Long>, EntityGraphQuerydslPredicateExecutor<QuizEntity> {

    Optional<QuizEntity> findById(final Long id, final EntityGraph entityGraph);

    List<QuizEntity> findByGameId(final Long gameId, final EntityGraph entityGraph);

    Optional<QuizEntity> findByGameIdAndBetIsNull(final Long gameId, final EntityGraph entityGraph);
}
