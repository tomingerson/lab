package de.fh_kiel.person.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fh_kiel.person.Person;
import de.fh_kiel.person.PersonService;

/**
 * @author jpr
 */
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/persons")
    public Collection<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @RequestMapping("/person/{id}")
    public Person getAllPersons(@PathVariable(value = "id") Long id) {
        return personService.getPersonById(null);
    }

}
