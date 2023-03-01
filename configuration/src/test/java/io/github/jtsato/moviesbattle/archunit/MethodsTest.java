package io.github.jtsato.moviesbattle.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import io.github.jtsato.moviesbattle.MoviesBattleApplication;

/**
 * @author Jorge Takeshi Sato
 */

@AnalyzeClasses(packagesOf = MoviesBattleApplication.class)
public class MethodsTest {

    @ArchTest
    static ArchRule all_public_methods_in_the_controller_layer_should_return_ResponseStatus =
            methods().that()
                    .areDeclaredInClassesThat()
                    .haveSimpleNameEndingWith("Controller")
                    .and()
                    .arePublic()
                    .should()
                    .beAnnotatedWith(ResponseStatus.class);

    @ArchTest
    static ArchRule code_units_in_Infra_layer_should_be_Transactional =
            classes().that()
            .haveSimpleNameEndingWith("Provider")
            .should()
            .beAnnotatedWith(Transactional.class);
}
