package de.fh_kiel.person;

import de.fh_kiel.CheckNull;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    @Transactional
    public Person createPerson(final Person person) {

        if (person.getId() == null) {
            return personDAO.save(person);
        }
        throw new IllegalStateException("ID must not be set in person " + person);
    }

    /**
     * Updates the passed person in our datastore.
     *
     * @param person the person to update
     */
    @CheckNull
    @Transactional
    public Person updatePerson(final Person person) {
        return personDAO.save(person);
    }

    /**
     * Deletes the passed person from our datastore.
     *
     * @param person the person to delete
     */
    @CheckNull
    @Transactional
    public void deletePerson(final Person person) {
        personDAO.delete(person);
    }

    /**
     * Trys to find the person with the given id
     *
     * @param id the id of the person
     * @return the person found or {@code null}, if no matching person was found
     */
    @CheckNull
    @Transactional
    public Optional<Person> getPersonById(final Long id) {
        return Optional.ofNullable(personDAO.findOne(id));
    }

    /**
     * @return all persons currently stored
     */
    @Transactional
    public List<Person> getAllPersons() {
        return personDAO.findAll(new Sort("lastName", "firstName"));
    }

    /**
     * Get all persons with programming skill in the supplied programmingLanguage
     *
     * @param programmingLanguage the programmingLanguage
     * @return the persons who have experience in programminng with the supplied programming language
     */
    @CheckNull
    @Transactional
    public Collection<Person> listPersons(final ProgrammingLanguage programmingLanguage) {

        return personDAO.findAll().stream()
                .filter(Objects::nonNull)
                .filter(Developer.class::isInstance)
                .map(Developer.class::cast)
                .filter(dev -> dev.getProgrammingLanguages().contains(programmingLanguage))
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(
                                (Comparator<Person>) (o1, o2) -> new CompareToBuilder()
                                        .append(o1.getLastName(), o2.getLastName())
                                        .append(o1.getFirstName(), o2.getFirstName())
                                        .toComparison()
                        ))
                );
    }
}
