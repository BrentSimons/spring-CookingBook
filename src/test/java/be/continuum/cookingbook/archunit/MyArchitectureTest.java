package be.continuum.cookingbook.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class MyArchitectureTest {

    @Test
    public void controllers_should_be_in_controller_package() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().areAnnotatedWith(RestController.class)
                .should().resideInAPackage("..controller..");

        rule.check(importedClasses);
    }

    @Test
    public void controllers_should_be_named_correctly() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().areAnnotatedWith(RestController.class)
                .should().haveSimpleNameEndingWith("Controller");

        rule.check(importedClasses);
    }

    @Test
    public void convertors_should_be_in_convertor_package() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().haveSimpleNameEndingWith("Convertor")
                .should().resideInAPackage("..convertor..");

        rule.check(importedClasses);
    }

    @Test
    public void convertors_should_be_components() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().haveSimpleNameEndingWith("Convertor")
                .should().beAnnotatedWith(Component.class);

        rule.check(importedClasses);
    }

    @Test
    public void dto_should_be_record_or_enum() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().resideInAPackage("..dto..")
                .should().beRecords()
                .orShould().beEnums();

        rule.check(importedClasses);
    }

    @Test
    public void dto_should_end_with_Json() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().resideInAPackage("..dto..")
                .should().haveSimpleNameEndingWith("Json");

        rule.check(importedClasses);
    }

    @Test
    public void exceptions_should_be_in_exception_package() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().resideInAPackage("..exception..")
                .should().haveSimpleNameEndingWith("Exception");

        rule.check(importedClasses);
    }

    @Test
    public void exceptions_should_extend_exceptions() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().resideInAPackage("..exception..")
                .should().beAssignableTo(Exception.class);

        rule.check(importedClasses);
    }

    @Test
    public void repositories_should_be_in_repository_package() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().areAnnotatedWith(Repository.class)
                .should().resideInAPackage("..repository..")
                .andShould().haveSimpleNameEndingWith("Repository")
                .orShould().haveSimpleNameEndingWith("RepositoryImpl");

        rule.check(importedClasses);
    }

    @Test
    public void services_should_be_in_service_package() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().areAnnotatedWith(Service.class)
                .should().resideInAPackage("..service..")
                .andShould().haveSimpleNameEndingWith("Service");

        rule.check(importedClasses);
    }

    @Test
    public void validators_should_be_in_validator_package() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("be.continuum");

        ArchRule rule = classes().that().haveSimpleNameEndingWith("Validator")
                .or().haveSimpleNameEndingWith("ValidatorImpl")
                .should().resideInAPackage("..validation..");

        rule.check(importedClasses);
    }

    @Test
    public void there_should_be_no_unused_imports() {

    }
}