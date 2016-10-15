package de.fh_kiel.person;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PersonServiceTest {

    private Developer developer1, developer2, developer3;
    private ProjectManager projectManager;

    @Before
    public void setup() {
        developer1 = new Developer("Mo", "Farah", LocalDate.of(1983, 10, 10), Gender.MALE, 1, 100000);
        developer1.addProgrammingLanguage("Java");

        developer2 = new Developer("Kevin", "Volland", LocalDate.of(1987, 5, 22), Gender.MALE, 5, 43000);
        developer2.addProgrammingLanguage("F#");

        developer3 = new Developer("Steven", "Gerrad", LocalDate.of(2005, 8, 9), Gender.MALE, 7, 79919);
        developer3.addProgrammingLanguage("F#");
        developer3.addProgrammingLanguage("C#");

        projectManager = new ProjectManager("Birgit", "Prinz", LocalDate.of(1989, 11, 8), Gender.FEMALE, 10, 60000, 3);
        projectManager.addProgrammingLanguage("Javascript");
        projectManager.addProgrammingLanguage("VBa");
        projectManager.addProgrammingLanguage("C#");
    }

    @Test
    public void listPersons_without_data() {
        PersonService personService = new PersonService();
        Collection<Person> list = personService.listPersons();
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 0 entries", list.size(), equalTo(0));
    }

    @Test
    public void listPersons_with_data() {
        PersonService personService = new PersonService(developer1, developer2, developer3, projectManager);
        List<Person> list = new ArrayList<>(personService.listPersons());
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 4 entries", list.size(), equalTo(4));

        assertThat("list should have correct order", list.get(0), equalTo(developer1));
        assertThat("list should have correct order", list.get(1), equalTo(developer3));
        assertThat("list should have correct order", list.get(2), equalTo(projectManager));
        assertThat("list should have correct order", list.get(3), equalTo(developer2));
    }

    @Test
    public void listPersonsProgrammingLanguage_without_data() {
        PersonService personService = new PersonService();

        Collection<Person> list = personService.listPersons(null);
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 0 entries", list.size(), equalTo(0));

        list = personService.listPersons("Java");
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 0 entries", list.size(), equalTo(0));
    }

    @Test
    public void listPersonsProgrammingLanguage_with_data() {
        PersonService personService = new PersonService(developer1, developer2, developer3, projectManager);

        List<Person> list = new ArrayList<>(personService.listPersons(null));
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 0 entries", list.size(), equalTo(0));

        list = new ArrayList<>(personService.listPersons("Java"));
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 1 entries", list.size(), equalTo(1));
        assertThat("list should have correct order", list.get(0), equalTo(developer1));

        list = new ArrayList<>(personService.listPersons("F#"));
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 2 entries", list.size(), equalTo(2));
        assertThat("list should have correct order", list.get(0), equalTo(developer3));
        assertThat("list should have correct order", list.get(1), equalTo(developer2));
    }
}
