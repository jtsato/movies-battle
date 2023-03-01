package io.github.jtsato.moviesbattle.infra.domains.player;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@Repository
public interface PlayerRepository extends EntityGraphJpaRepository<PlayerEntity, Long>, EntityGraphQuerydslPredicateExecutor<PlayerEntity> {

    Optional<PlayerEntity> findByEmailIgnoreCase(final String email);
}
