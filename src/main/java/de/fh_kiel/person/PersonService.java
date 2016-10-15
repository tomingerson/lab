package de.fh_kiel.person;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PersonService {

    private final Set<Person> persons;

    /**
     * Constructor
     *
     * @param persons persons in the pool
     */
    public PersonService(final Person... persons) {
        this.persons = new HashSet<>(Arrays.asList(persons));
    }


    /**
     * Get all persons
     *
     * @return all the persons
     */
    public Collection<Person> listPersons() {
        return new TreeSet<>(persons);
    }

    /**
     * Get all persons with programming skill in the supplied programmingLanguage
     *
     * @param programmingLanguage the programmingLanguage
     * @return the persons who have experience in programminng with the supplied programming language
     */
    public Collection<Person> listPersons(final String programmingLanguage) {
        final Collection<Person> result = new TreeSet<>();

        for (final Person person : persons) {
            if (!(person instanceof Developer)) {
                continue;
            }

            final Developer developer = (Developer) person;
            for (String currProgrammingLanguage : developer.getProgrammingLanguages()) {
                if (currProgrammingLanguage.equals(programmingLanguage)) {
                    result.add(developer);
                    break;
                }
            }
        }

        return result;
    }
}
