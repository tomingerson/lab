package de.fh_kiel.person;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.*;

/**
 * DataAccessObject to handle {@link Person persons}. Uses an internal storage, no database yet, no transactions yet.
 * Designed to be a singleton.
 *
 * @author Created by tom on 15.10.2016.
 */
public class PersonDAO {

    private final Set<Person> persons;

    public PersonDAO(final Person... persons) {
        this.persons = new HashSet<>(Arrays.asList(persons));
    }

    /**
     * takes the person and saves it.
     *
     * @param person newly created person
     */
    public void createPerson(final Person person) {
        assert person.getId() == null;

        final Long maxId = Collections.max(persons, new Comparator<Person>() {
            @Override
            public int compare(final Person o1, final Person o2) {
                final CompareToBuilder ctb = new CompareToBuilder();
                ctb.append(o1.getId(), o2.getId());
                return ctb.toComparison();
            }
        }).getId() + 1L;
        person.setId(maxId);
        persons.add(person);
    }

    public void updatePerson(final Person person) {
        assert person.getId() != null;

        // there should be nothing to be done here since the persons with an id originate
        persons.add(person);
    }

    /**
     * finds all persons known by the system.
     *
     * @return list of all known persons
     */
    public List<Person> getAllPersons() {
        final ArrayList<Person> list = new ArrayList<>(persons);
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(final Person o1, final Person o2) {
                return new CompareToBuilder().append(o1.getLastName(), o2.getLastName()).append
                        (o1.getFirstName(), o2.getFirstName()).toComparison();
            }
        });
        return list;
    }

    /**
     * finds a person with the given id. if not found, an exception is thrown
     *
     * @param id to look for
     * @return the person found, otherwise an exception
     */
    public Person getPerson(final Long id) {

        Person result = null;

        for (final Person p : persons) {
            if (Objects.equals(p.getId(), id)) {
                result = p;
                break;
            }
        }

        if (result == null) {
            throw new IllegalStateException("no person found with id " + id);
        }

        return result;
    }
}
