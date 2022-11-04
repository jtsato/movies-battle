package io.github.jtsato.moviesbattle.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.freeze.FreezingArchRule.freeze;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import io.github.jtsato.moviesbattle.MoviesBattleApplication;

/**
 * @author Jorge Takeshi Sato
 */

@AnalyzeClasses(packagesOf = MoviesBattleApplication.class)
public class FrozenRulesTest {

    @ArchTest
    static final ArchRule no_classes_should_use_the_exception_handler = freeze(noClasses().should()
                                                                                          .dependOnClassesThat()
                                                                                          .areAssignableTo(MoviesBattleApplication.class));
}
