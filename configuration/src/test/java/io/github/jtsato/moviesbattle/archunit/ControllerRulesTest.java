package io.github.jtsato.moviesbattle.archunit;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.PackageMatchers;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.github.jtsato.moviesbattle.MoviesBattleApplication;

import static com.tngtech.archunit.core.domain.JavaClass.Functions.GET_PACKAGE_NAME;
import static com.tngtech.archunit.core.domain.JavaMember.Predicates.declaredIn;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * @author Jorge Takeshi Sato
 */

@AnalyzeClasses(packagesOf = MoviesBattleApplication.class, importOptions = ImportOption.DoNotIncludeTests.class)
public class ControllerRulesTest {

    @ArchTest
    static final ArchRule controllers_should_only_call_secured_methods = classes().that()
            .haveSimpleNameEndingWith("Controller")
            .should()
            .onlyCallMethodsThat(allowedPackages());

    @ArchTest
    static final ArchRule controllers_should_only_call_secured_constructors = classes().that()
            .haveSimpleNameEndingWith("Controller")
            .should()
            .onlyCallConstructorsThat(allowedPackages());

    @ArchTest
    static final ArchRule controllers_should_only_call_secured_code_units = classes().that()
            .haveSimpleNameEndingWith("Controller")
            .should()
            .onlyCallCodeUnitsThat(allowedPackages());

    @ArchTest
    static final ArchRule controllers_should_only_access_secured_fields = classes().that()
            .haveSimpleNameEndingWith("Controller")
            .should()
            .onlyAccessFieldsThat(allowedPackages());

    @ArchTest
    static final ArchRule controllers_should_only_access_secured_members = classes().that()
            .haveSimpleNameEndingWith("Controller")
            .should()
            .onlyAccessMembersThat(allowedPackages());

    private static DescribedPredicate<JavaMember> allowedPackages() {
        return areDeclaredInController()
                .or(areDeclaredInUseCase())
                .or(areDeclaredInMapper())
                .or(areDeclaredInRestDomains())
                .or(areDeclaredInRestCommon())
                .or(areDeclaredInSpringFramework())
                .or(areDeclaredInApacheCommons())
                .or(areLogger())
                .or(areDeclaredInByJacoco());
    }

    private static DescribedPredicate<JavaMember> areDeclaredInController() {
        final DescribedPredicate<JavaClass> aPackageController = GET_PACKAGE_NAME
                .is(PackageMatchers.of("..controller", "java.."))
                .as("a package '..controller'");
        return are(declaredIn(aPackageController));
    }

    private static DescribedPredicate<JavaMember> areDeclaredInUseCase() {
        final DescribedPredicate<JavaClass> aPackageController = GET_PACKAGE_NAME
                .is(PackageMatchers.of("..usecase..", "java.."))
                .as("a package '..usecase..'");
        return are(declaredIn(aPackageController));
    }

    private static DescribedPredicate<JavaMember> areDeclaredInMapper() {
        final DescribedPredicate<JavaClass> aPackageController = GET_PACKAGE_NAME
                .is(PackageMatchers.of("..mapper..", "java.."))
                .as("a package '..mapper..'");
        return are(declaredIn(aPackageController));
    }

    private static DescribedPredicate<JavaMember> areDeclaredInRestDomains() {
        final DescribedPredicate<JavaClass> aPackageController = GET_PACKAGE_NAME
                .is(PackageMatchers.of("..rest..domains..", "java.."))
                .as("a package '..domains..'");
        return are(declaredIn(aPackageController));
    }

    private static DescribedPredicate<JavaMember> areDeclaredInRestCommon() {
        final DescribedPredicate<JavaClass> aPackageController = GET_PACKAGE_NAME
                .is(PackageMatchers.of("..rest..common..", "java.."))
                .as("a package '..common..'");
        return are(declaredIn(aPackageController));
    }

    private static DescribedPredicate<JavaMember> areDeclaredInSpringFramework() {
        final DescribedPredicate<JavaClass> aPackageController = GET_PACKAGE_NAME
                .is(PackageMatchers.of("..org.springframework..", "java.."))
                .as("a package '..springframework..'");
        return are(declaredIn(aPackageController));
    }

    private static DescribedPredicate<JavaMember> areDeclaredInApacheCommons() {
        final DescribedPredicate<JavaClass> aPackageController = GET_PACKAGE_NAME
                .is(PackageMatchers.of("..apache.commons..", "java.."))
                .as("a package '..apache.commons..'");
        return are(declaredIn(aPackageController));
    }

    private static DescribedPredicate<JavaMember> areLogger() {
        return are(declaredIn(org.slf4j.Logger.class))
                .or(declaredIn(org.slf4j.LoggerFactory.class));
    }

    private static DescribedPredicate<JavaMember> areDeclaredInByJacoco() {
        DescribedPredicate<JavaClass> aPackageController = GET_PACKAGE_NAME
                .is(PackageMatchers.of("..org.jacoco.agent..", "java.."))
                .as("a package '..org.jacoco.agent..'");
        return are(declaredIn(aPackageController));
    }
}
