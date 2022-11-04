package io.github.jtsato.moviesbattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;

/**
 * @author Jorge Takeshi Sato
 */

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = MoviesBattleApplication.class, repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
public class MoviesBattleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MoviesBattleApplication.class, args);
    }
}