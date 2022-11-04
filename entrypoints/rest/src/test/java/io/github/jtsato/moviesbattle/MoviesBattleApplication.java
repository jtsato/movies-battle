package io.github.jtsato.moviesbattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jorge Takeshi Sato
 */

@SpringBootApplication(scanBasePackageClasses = {MoviesBattleApplication.class})
public class MoviesBattleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MoviesBattleApplication.class);
    }
}
