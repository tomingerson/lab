package de.fh_kiel.person;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
        developer1 = new Developer(1, 100000, new HashSet<>(Collections.singleton("Java")));
        developer1.setId(1L);
        developer1.setFirstName("Mo");
        developer1.setLastName("Farah");
        developer1.setDayOfBirth(LocalDate.of(1983, 10, 10));
        developer1.setGender(Gender.MALE);

        developer2 = new Developer(5, 43000, new HashSet<>(Collections.singleton("F#")));
        developer2.setId(2L);
        developer2.setFirstName("Kevin");
        developer2.setLastName("Volland");
        developer2.setDayOfBirth(LocalDate.of(1987, 5, 22));
        developer2.setGender(Gender.MALE);

        developer3 = new Developer(7, 79919, new HashSet<>(Arrays.asList("F#", "C#")));
        developer3.setId(3L);
        developer3.setFirstName("Steven");
        developer3.setLastName("Gerrad");
        developer3.setDayOfBirth(LocalDate.of(2005, 8, 9));
        developer3.setGender(Gender.MALE);

        projectManager = new ProjectManager(3);
        projectManager.setId(4L);
        projectManager.setFirstName("Birgit");
        projectManager.setLastName("Prinz");
        projectManager.setDayOfBirth(LocalDate.of(1989, 11, 8));
        projectManager.setGender(Gender.FEMALE);
        projectManager.setExperienceInYears(10);
        projectManager.setMinimumSalary(60000);
        projectManager.setProgrammingLanguages(new HashSet<>(Arrays.asList("Javascript", "VBa", "C#")));
    }

    @Test
    public void getAllPersons() {

        when(mockPersonDAO.getAllPersons()).thenReturn(Arrays.asList(developer1, developer2, developer3, projectManager));

        final List<Person> list = new ArrayList<>(personService.getAllPersons());
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 4 entries", list, hasSize(4));
        assertThat("wrong order of developers in result", list, contains(developer1, developer2, developer3, projectManager));

        verify(mockPersonDAO, times(1)).getAllPersons();
    }

    @Test
    public void listPersonsProgrammingLanguage() {

        when(mockPersonDAO.getAllPersons()).thenReturn(Arrays.asList(developer1, developer2, developer3, projectManager));

        List<Person> list = new ArrayList<>(personService.listPersons(null));
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 0 entries", list, empty());

        list = new ArrayList<>(personService.listPersons("Java"));
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 1 entries", list, hasSize(1));
        assertThat("list should have correct order", list, contains(developer1));

        list = new ArrayList<>(personService.listPersons("F#"));
        assertThat("list shouldn't be null", list, notNullValue());
        assertThat("list should have 2 entries", list, hasSize(2));
        assertThat("list should have correct order", list, contains(developer3, developer2));

        verify(mockPersonDAO, times(3)).getAllPersons();
    }
}
