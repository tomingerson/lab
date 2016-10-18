package de.fh_kiel.person;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * This Service handles basics of {@link Person persons}. No Transactions yet.
 *
 * @author ergouser
 */
public class PersonService {

    private final PersonDAO personDAO;


    public PersonService(final PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /**
     * Get all persons
     *
     * @return all the persons
     */
    public Collection<Person> listPersons() {
        return personDAO.getAllPersons();
    }

    /**
     * Get all persons with programming skill in the supplied programmingLanguage
     *
     * @param programmingLanguage the programmingLanguage
     * @return the persons who have experience in programminng with the supplied programming language
     */
    public Collection<Person> listPersons(final String programmingLanguage) {
        final Collection<Person> result = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(final Person o1, final Person o2) {
                return new CompareToBuilder().append(o1.getLastName(), o2.getLastName()).append
                        (o1.getFirstName(), o2.getFirstName()).toComparison();
            }
        });

        for (final Person person : personDAO.getAllPersons()) {
            if (!(person instanceof Developer)) {
                continue;
            }

            final Developer developer = (Developer) person;
            for (final String currProgrammingLanguage : developer.getProgrammingLanguages()) {
                if (currProgrammingLanguage.equals(programmingLanguage)) {
                    result.add(developer);
                    break;
                }
            }
        }

        return result;
    }
}
