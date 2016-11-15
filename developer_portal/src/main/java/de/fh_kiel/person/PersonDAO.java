package de.fh_kiel.person;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author Created by tom on 15.10.2016.
 */
@Repository
public class PersonDAO {

    private final Map<Long, Person> persons = new HashMap<>();

    /**
     * Default constructor for spring
     */
    public PersonDAO() {
    }

    /**
     * constructor taking some persons and storing them<br/>
     * used mainly for tests
     * @param persons they are stored by default
     */
    public PersonDAO(Person... persons) {
        for (Person person : persons) {
            this.persons.put(person.getId(), person);
        }
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

        final Long maxId = persons.isEmpty() ? 0L : Collections.max(persons.keySet(), (o1, o2) ->
                new CompareToBuilder().append(o1, o2).toComparison()
        );
        person.setId(maxId + 1L);
        persons.put(person.getId(), person);
    }

    /**
     * Updates the passed person in our datastore.
     *
     * @param person the person to update
     */
    public void updatePerson(final Person person) {
        // Remove the old version of the passed person
        deletePerson(person);
        persons.put(person.getId(), person);
    }

    /**
     * Deletes the passed person from our datastore.
     *
     * @param person the person to delete
     */
    public void deletePerson(final Person person) {
        persons.remove(person.getId());
    }

    /**
     * Trys to find the person with the given id
     *
     * @param id the id of the person
     * @return the person found or {@code null}, if no matching person was found
     */
    public Person getPersonById(final Long id) {
        Person p = persons.get(id);
        if (p == null)
            throw new IllegalStateException("no person found with id " + id);
        return p;
    }

    /**
     * @return all persons currently stored
     */
    public Collection<Person> getAllPersons() {
        // return a defensive copy to protect the values and hide the internal implementation
        return new ArrayList<>(persons.values());
    }
}
