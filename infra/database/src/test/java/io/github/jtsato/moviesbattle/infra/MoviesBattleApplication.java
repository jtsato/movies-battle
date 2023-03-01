package io.github.jtsato.moviesbattle.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;

/**
 * @author Jorge Takeshi Sato
 */

@EnableJpaRepositories(basePackageClasses = MoviesBattleApplication.class, repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
@SpringBootApplication
public class MoviesBattleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MoviesBattleApplication.class, args);
    }
}
