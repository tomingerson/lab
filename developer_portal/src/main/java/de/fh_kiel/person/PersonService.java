package de.fh_kiel.person;

import de.fh_kiel.CheckNull;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Service implementation for {@link Person}
 *
 * @author jpr
 */
@Service
public class PersonService {

    private final PersonDAO personDAO;


    /**
     * Constructor
     *
     * @param personDAO the data storage for instances of {@link Person}
     */
    @Autowired
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /**
     * Stores the passed person.
     *
     * @param person the person to create
     */
    @CheckNull
    public void createPerson(final Person person) {
        personDAO.createPerson(person);
    }

    /**
     * Updates the passed person in our datastore.
     *
     * @param person the person to update
     */
    @CheckNull
    public void updateCompany(final Person person) {
        personDAO.updatePerson(person);
    }

    /**
     * Deletes the passed person from our datastore.
     *
     * @param person the person to delete
     */
    @CheckNull
    public void deletePerson(final Person person) {
        personDAO.deletePerson(person);
    }

    /**
     * Trys to find the person with the given id
     *
     * @param id the id of the person
     * @return the person found or {@code null}, if no matching person was found
     */
    @CheckNull
    public Person getPersonById(final Long id) {
        return personDAO.getPersonById(id);
    }

    /**
     * @return all persons currently stored
     */
    public Collection<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }

    /**
     * Get all persons with programming skill in the supplied programmingLanguage
     *
     * @param programmingLanguage the programmingLanguage
     * @return the persons who have experience in programminng with the supplied programming language
     */
    @CheckNull
    public Collection<Person> listPersons(final String programmingLanguage) {

        final Collection<Person> result = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(final Person o1, final Person o2) {
                return new CompareToBuilder()
                        .append(o1.getLastName(), o2.getLastName())
                        .append(o1.getFirstName(), o2.getFirstName())
                        .toComparison();
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
