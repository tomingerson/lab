package de.fh_kiel.person;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

/**
 * <a href="http://www.adam-bien.com/roller/abien/entry/java_8_flatmap_example">Thanks, Adam Bien, for
 * using the same examples ;-)</a>
 *
 * @author Created by tom on 01.11.2016.
 */
public class TeamTest {
    private Developer developer1, developer2, developer3, developer4;

    @Before
    public void setup() {

        final ProgrammingLanguage plJava = new ProgrammingLanguage();
        plJava.setName("Java");
        final ProgrammingLanguage plFSharp = new ProgrammingLanguage();
        plFSharp.setName("F#");
        final ProgrammingLanguage plCSharp = new ProgrammingLanguage();
        plCSharp.setName("C#");
        final ProgrammingLanguage plCobol = new ProgrammingLanguage();
        plCobol.setName("Cobol");
        final ProgrammingLanguage plFortran = new ProgrammingLanguage();
        plFortran.setName("Fortran");

        developer1 = new Developer();
        developer1.setId(1L);
        developer1.setFirstName("Mo");
        developer1.setLastName("Farah");
        developer1.setDayOfBirth(LocalDate.of(1983, 10, 10));
        developer1.setGender(Gender.MALE);
        developer1.setExperienceInYears(1);
        developer1.setMinimumSalary(100000);
        developer1.getProgrammingLanguages().add(plJava);

        developer2 = new Developer();
        developer2.setId(2L);
        developer2.setFirstName("Kevin");
        developer2.setLastName("Volland");
        developer2.setDayOfBirth(LocalDate.of(1987, 5, 22));
        developer2.setGender(Gender.MALE);
        developer2.setExperienceInYears(5);
        developer2.setMinimumSalary(43000);
        developer2.getProgrammingLanguages().add(plFSharp);

        developer3 = new Developer();
        developer3.setId(3L);
        developer3.setFirstName("Steven");
        developer3.setLastName("Gerrad");
        developer3.setDayOfBirth(LocalDate.of(2005, 8, 9));
        developer3.setGender(Gender.MALE);
        developer3.setExperienceInYears(7);
        developer3.setMinimumSalary(79919);
        developer3.getProgrammingLanguages().add(plFSharp);
        developer3.getProgrammingLanguages().add(plCSharp);

        developer4 = new Developer();
        developer4.setId(42L);
        developer4.setFirstName("Caitlyn");
        developer4.setLastName("Jenner");
        developer4.setDayOfBirth(LocalDate.of(1949, 10, 28));
        developer4.setGender(Gender.FEMALE);
        developer4.setExperienceInYears(1);
        developer4.setMinimumSalary(85000);
        developer4.getProgrammingLanguages().add(plCobol);
        developer4.getProgrammingLanguages().add(plFortran);
    }

    @Test
    public void testTeamSkills() {

        List<Developer> team = new ArrayList<>();
        team.add(developer1);
        team.add(developer2);
        team.add(developer3);
        team.add(developer4);

        Stream<Developer> developers = team.stream();
        Stream<Set<ProgrammingLanguage>> developerLanguages = developers.map(Developer::getProgrammingLanguages);
        Stream<ProgrammingLanguage> allLanguages = developerLanguages.flatMap(Collection::stream);

        Set<ProgrammingLanguage> languages = allLanguages.collect(Collectors.toSet());

        assertTrue(languages.containsAll(developer1.getProgrammingLanguages()));
        assertTrue(languages.containsAll(developer2.getProgrammingLanguages()));
        assertTrue(languages.containsAll(developer3.getProgrammingLanguages()));
        assertTrue(languages.containsAll(developer4.getProgrammingLanguages()));
    }
}
