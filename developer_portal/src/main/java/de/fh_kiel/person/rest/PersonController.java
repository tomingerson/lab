package de.fh_kiel.person.rest;

import de.fh_kiel.person.Person;
import de.fh_kiel.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

/**
 * @author jpr
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Person> createPerson(@RequestBody final Person p) {
        try {
            final Person person = this.personService.createPerson(p);
            final URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(person.getId())
                    .toUri();
            return ResponseEntity.created(location).body(person);
        } catch (Exception e) {
            throw new EntityMalformedException("Person could not be created", e);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Person[] getAllPersons() {
        final Collection<Person> allPersons = personService.getAllPersons();
        return allPersons.toArray(new Person[allPersons.size()]);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable(value = "id") final Long id) {
        return personService.getPersonById(id).orElseThrow(() ->
                new EntityNotFoundException("Person with id '" + id + "' could not be " +
                        "found"));

    }
}
