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
 * Thanks Adam Bien for using the same examples ;-)
 * <a href="http://www.adam-bien.com/roller/abien/entry/java_8_flatmap_example">Thanks, Adam Bien, for
 * using the same examples ;-)</a>
 *
 * @author Created by tom on 01.11.2016.
 */
public class TeamTest {
    private Developer developer1, developer2, developer3, developer4;

    @Before
    public void setup() {

        developer1 = new Developer(1L, "Mo", "Farah", LocalDate.of(1983, 10, 10), Gender.MALE, 1, 100000);
        developer1.addProgrammingLanguage("Java");

        developer2 = new Developer(2L, "Kevin", "Volland", LocalDate.of(1987, 5, 22), Gender.MALE, 5,
                43000);
        developer2.addProgrammingLanguage("F#");

        developer3 = new Developer(3L, "Steven", "Gerrad", LocalDate.of(2005, 8, 9), Gender.MALE, 7,
                79919);
        developer3.addProgrammingLanguage("F#");
        developer3.addProgrammingLanguage("C#");

        developer4 = new Developer(42L, "Caitlyn", "Jenner", LocalDate.of(1949, 10, 28), Gender
                .FEMALE, 1, 85000);
        developer4.addProgrammingLanguage("Cobol");
        developer4.addProgrammingLanguage("Fortran");

    }

    @Test
    public void testTeamSkills() {

        List<Developer> team = new ArrayList<>();
        team.add(developer1);
        team.add(developer2);
        team.add(developer3);
        team.add(developer4);

        Stream<Developer> developers = team.stream();
        Stream<Set<String>> developerLanguages = developers.map(Developer::getProgrammingLanguages);
        Stream<String> allLanguages = developerLanguages.flatMap(Collection::stream);

        Set<String> languages = allLanguages.collect(Collectors.toSet());

        assertTrue(languages.containsAll(developer1.getProgrammingLanguages()));
        assertTrue(languages.containsAll(developer2.getProgrammingLanguages()));
        assertTrue(languages.containsAll(developer3.getProgrammingLanguages()));
        assertTrue(languages.containsAll(developer4.getProgrammingLanguages()));
    }
}
