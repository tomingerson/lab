package de.fh_kiel.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.stereotype.Repository;

/**
 * @author Created by tom on 15.10.2016.
 */
@Repository
public class PersonDAO {

    private final Set<Person> persons;

    /**
     * Default constructor for spring
     */
    public PersonDAO() {
        this.persons = new HashSet<>();
    }

    public PersonDAO(Person... persons) {
        this.persons = new HashSet<>(Arrays.asList(persons));
    }


    /**
     * Stores the passed person.
     *
     * @param person the person to create
     */
    public void createPerson(final Person person) {
        if (person.getId() != null) {
            throw new IllegalArgumentException("Passed person is not new");
        }
        final Long maxId = Collections.max(persons, (o1, o2) -> {
            final CompareToBuilder ctb = new CompareToBuilder();
            ctb.append(o1.getId(), o2.getId());
            return ctb.toComparison();
        }).getId();
        person.setId(maxId + 1);
        persons.add(person);
    }

    /**
     * Updates the passed person in our datastore.
     *
     * @param person the person to update
     */
    public void updatePerson(final Person person) {
        // Remove the old version of the passed person
        deletePerson(person);
        persons.add(person);
    }

    /**
     * Deletes the passed person from our datastore.
     *
     * @param person the person to delete
     */
    public void deletePerson(final Person person) {
        persons.remove(person);
    }

    /**
     * Trys to find the person with the given id
     *
     * @param id the id of the person
     * @return the person found or {@code null}, if no matching person was found
     */
    public Person getPersonById(final Long id) {
        return persons.stream().filter(c -> c.getId().equals(id)).findFirst().orElseThrow(() -> new IllegalStateException("no person found with id " + id));
    }

    /**
     * @return all persons currently stored
     */
    public Collection<Person> getAllPersons() {
        return new ArrayList<>(persons);
    }
}
