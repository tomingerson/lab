package de.fh_kiel.person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

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
        developer1 = new Developer(1L, "Mo", "Farah", LocalDate.of(1983, 10, 10), Gender.MALE, 1,
                100000, "Java");

        developer2 = new Developer(2L, "Kevin", "Volland", LocalDate.of(1987, 5, 22), Gender.MALE, 5,
                43000, "F#");

        developer3 = new Developer(3L, "Steven", "Gerrad", LocalDate.of(2005, 8, 9), Gender.MALE, 7,
                79919, "F#", "C#");

        projectManager = new ProjectManager(new Developer(4L, "Birgit", "Prinz", LocalDate.of(1989, 11,
                8), Gender.FEMALE, 10, 60000, "Javascript", "VBa", "C#"));
        projectManager.setExperienceInYearsAsPM(3);
    }

    @Test
    public void getAllPersons() {

        when(mockPersonDAO.getAllPersons()).thenReturn(Arrays.asList(developer1, developer2,
                developer3, projectManager.getDeveloper()));

        final Collection<Person> persons = personService.getAllPersons();
        assertThat("persons shouldn't be null", persons, notNullValue());
        assertThat("persons should have 4 entries", persons, hasSize(4));
        assertThat("wrong order of developers in result", persons, contains(developer1, developer2,
                developer3, projectManager.getDeveloper()));

        verify(mockPersonDAO, times(1)).getAllPersons();
    }

    @Test
    public void listPersonsProgrammingLanguage() {

        when(mockPersonDAO.getAllPersons()).thenReturn(Arrays.asList(developer1, developer2,
                developer3, projectManager.getDeveloper()));

        final Collection<Person> personsNull = personService.listPersons(null);
        assertThat("list shouldn't be null", personsNull, notNullValue());
        assertThat("list should have 0 entries", personsNull, empty());

        final Collection<Person> personsJava = personService.listPersons("Java");
        assertThat("list shouldn't be null", personsJava, notNullValue());
        assertThat("list should have 1 entries", personsJava, hasSize(1));
        assertThat("list should have correct order", personsJava, contains(developer1));

        final Collection<Person> personsFSharp = personService.listPersons("F#");
        assertThat("list shouldn't be null", personsFSharp, notNullValue());
        assertThat("list should have 2 entries", personsFSharp, hasSize(2));
        assertThat("list should have correct order", personsFSharp, contains(developer3, developer2));

        verify(mockPersonDAO, times(3)).getAllPersons();
    }
}
