package de.fh_kiel.person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
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

    private ProgrammingLanguage plJava, plFSharp, plCSharp, plCobol, plJavaScript, plVBA;


    @Before
    public void setup() {
        plJava = new ProgrammingLanguage();
        plJava.setName("Java");
        plFSharp = new ProgrammingLanguage();
        plFSharp.setName("F#");
        plCSharp = new ProgrammingLanguage();
        plCSharp.setName("C#");
        plCobol = new ProgrammingLanguage();
        plCobol.setName("Cobol");
        plJavaScript = new ProgrammingLanguage();
        plJavaScript.setName("JavaScript");
        plVBA = new ProgrammingLanguage();
        plVBA.setName("VBA");

        developer1 = new Developer();
        developer1.setId(1L);
        developer1.setFirstName("Mo");
        developer1.setLastName("Farah");
        developer1.setDayOfBirth(LocalDate.of(1983, 10, 10));
        developer1.setGender(Gender.MALE);
        developer1.setExperienceInYears(1);
        developer1.setMinimumSalary(100000);
        developer1.getProgrammingLanguages().add(plJava);

        developer2 = new Developer();
        developer2.setId(2L);
        developer2.setFirstName("Kevin");
        developer2.setLastName("Volland");
        developer2.setDayOfBirth(LocalDate.of(1987, 5, 22));
        developer2.setGender(Gender.MALE);
        developer2.setExperienceInYears(5);
        developer2.setMinimumSalary(43000);
        developer2.getProgrammingLanguages().add(plFSharp);

        developer3 = new Developer();
        developer3.setId(3L);
        developer3.setFirstName("Steven");
        developer3.setLastName("Gerrad");
        developer3.setDayOfBirth(LocalDate.of(2005, 8, 9));
        developer3.setGender(Gender.MALE);
        developer3.setExperienceInYears(7);
        developer3.setMinimumSalary(79919);
        developer3.getProgrammingLanguages().add(plFSharp);
        developer3.getProgrammingLanguages().add(plCSharp);

        projectManager = new ProjectManager();
        projectManager.setExperienceInYearsAsPM(3);
        final Developer dev = new Developer();
        dev.setId(4L);
        dev.setFirstName("Birgit");
        dev.setLastName("Prinz");
        dev.setDayOfBirth(LocalDate.of(1989, 11, 8));
        dev.setGender(Gender.FEMALE);
        dev.setExperienceInYears(10);
        dev.setMinimumSalary(60000);
        dev.getProgrammingLanguages().add(plCSharp);
        dev.getProgrammingLanguages().add(plJavaScript);
        dev.getProgrammingLanguages().add(plVBA);
        projectManager.setDeveloper(dev);
    }

    @Test
    public void getAllPersons() {

        when(mockPersonDAO.findAll((Sort) any())).thenReturn(Arrays
                .asList(developer1,
                        developer2,
                        developer3, projectManager.getDeveloper()));

        final Collection<Person> persons = personService.getAllPersons();
        assertThat("persons shouldn't be null", persons, notNullValue());
        assertThat("persons should have 4 entries", persons, hasSize(4));
        assertThat("wrong order of developers in result", persons, contains(developer1, developer2,
                developer3, projectManager.getDeveloper()));

        verify(mockPersonDAO, times(1)).findAll(eq(new Sort("lastName", "firstName")));
    }

    @Test
    public void listPersonsProgrammingLanguage() {

        when(mockPersonDAO.findAll()).thenReturn(Arrays.asList(developer1, developer2,
                developer3, projectManager.getDeveloper()));

        final Collection<Person> personsNull = personService.listPersons(null);
        assertThat("list shouldn't be null", personsNull, notNullValue());
        assertThat("list should have 0 entries", personsNull, empty());

        final Collection<Person> personsJava = personService.listPersons(plJava);
        assertThat("list shouldn't be null", personsJava, notNullValue());
        assertThat("list should have 1 entries", personsJava, hasSize(1));
        assertThat("list should have correct order", personsJava, contains(developer1));

        final Collection<Person> personsFSharp = personService.listPersons(plFSharp);
        assertThat("list shouldn't be null", personsFSharp, notNullValue());
        assertThat("list should have 2 entries", personsFSharp, hasSize(2));
        assertThat("list should have correct order", personsFSharp, contains(developer3, developer2));

        verify(mockPersonDAO, times(3)).findAll();
    }
}
