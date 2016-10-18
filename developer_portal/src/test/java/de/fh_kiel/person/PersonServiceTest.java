package de.fh_kiel.person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonDAO mockPersonDAO;

    private Developer developer1, developer2, developer3;
    private ProjectManager projectManager;


    @Before
    public void setup() {
        developer1 = new Developer(1L, "Mo", "Farah", LocalDate.of(1983, 10, 10), Gender.MALE, 1, 100000);
        developer1.addProgrammingLanguage("Java");

        developer2 = new Developer(2L, "Kevin", "Volland", LocalDate.of(1987, 5, 22), Gender.MALE, 5,
                43000);
        developer2.addProgrammingLanguage("F#");

        developer3 = new Developer(3L, "Steven", "Gerrad", LocalDate.of(2005, 8, 9), Gender.MALE, 7,
                79919);
        developer3.addProgrammingLanguage("F#");
        developer3.addProgrammingLanguage("C#");

        projectManager = new ProjectManager(4L, "Birgit", "Prinz", LocalDate.of(1989, 11, 8), Gender
                .FEMALE, 10, 60000, 3);
        projectManager.addProgrammingLanguage("Javascript");
        projectManager.addProgrammingLanguage("VBa");
        projectManager.addProgrammingLanguage("C#");
    }

    @Test
    public void listPersons() {

        when(mockPersonDAO.getAllPersons()).thenReturn(Arrays.asList(developer1, developer2,
                developer3, projectManager));

        final List<Person> list = new ArrayList<>(personService.listPersons());
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 4 entries", list, hasSize(4));
        assertThat("wrong order of developers in result", list, contains(developer1, developer2,
                developer3, projectManager));

        verify(mockPersonDAO, times(1)).getAllPersons();
    }

    @Test
    public void listPersonsProgrammingLanguage() {

        when(mockPersonDAO.getAllPersons()).thenReturn(Arrays.asList(developer1, developer2,
                developer3, projectManager));

        List<Person> list = new ArrayList<>(personService.listPersons(null));
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 0 entries", list, empty());

        list = new ArrayList<>(personService.listPersons("Java"));
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 1 entries", list, hasSize(1));
        assertThat("list should have correct order 1", list, contains(developer1));

        list = new ArrayList<>(personService.listPersons("F#"));
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 2 entries", list, hasSize(2));
        assertThat("list should have correct order 2", list, contains(developer3, developer2));

        verify(mockPersonDAO, times(3)).getAllPersons();
    }
}
