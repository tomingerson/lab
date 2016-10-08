package de.fh_kiel.person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Created by tom on 08.10.2016.
 */
public class PersonService {

    private static final List<Developer> developers = new ArrayList<>();

    static {
        final Developer dev1 = new Developer();
        dev1.setBirthdate(LocalDate.of(1986, 10, 10));
        dev1.setSex("f");
        dev1.setGivenName("Jane");
        dev1.setSurName("Doe");
        dev1.setMinimumSalary(35000d);
        dev1.setYearsOfExperience(2L);
        dev1.addNewLanguage(ProgrammingLanguage.JAVA);
        dev1.addNewLanguage(ProgrammingLanguage.JAVA_SCRIPT);
        developers.add(dev1);

        final Developer dev2 = new Developer();
        dev2.setBirthdate(LocalDate.of(1969, 1, 25));
        dev2.setGivenName("John");
        dev2.setSurName("Doe");
        dev2.setSex("m");
        dev2.setYearsOfExperience(15L);
        dev2.setMinimumSalary(65000d);
        dev2.addNewLanguage(ProgrammingLanguage.JAVA);
        dev2.addNewLanguage(ProgrammingLanguage.C_PLUSPLUS);
        dev2.addNewLanguage(ProgrammingLanguage.DOT_NET);
        dev2.addNewLanguage(ProgrammingLanguage.C_SHARP);
        developers.add(dev2);
    }

    /**
     * Lists all known Devs
     *
     * @return List of all known Developers
     */
    public final List<Developer> getAllDevelopers() {
        return new ArrayList<>(developers);
    }

    /**
     * Filters all the Devs for a given Language.
     *
     * @param pl Language that the Dev needs to know
     * @return developers, who know the given language, never {@code null}
     */
    public final List<Developer> getDevelopersByPL(final ProgrammingLanguage pl) {
        return developers.stream().filter(d -> d.getKnownLanguages().contains(pl)).collect(Collectors
                .toList());

    }
}
