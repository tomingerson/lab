package de.fh_kiel.person;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

/**
 * @author Created by tom on 08.10.2016.
 */
public class PersonServiceTest {

    private final PersonService personService = new PersonService();

    @Test
    @Ignore
    public void getAllDevelopers() throws Exception {

    }

    @Test
    public void getDevelopersByPL() throws Exception {
        final List<Developer> perlDevs = personService.getDevelopersByPL(ProgrammingLanguage.PERL);
        Assert.assertThat("found a dev that should not exist", perlDevs, IsEmptyCollection.empty());

        final List<Developer> javaDevs = personService.getDevelopersByPL(ProgrammingLanguage.JAVA);
        Assert.assertThat("should contain all devs", javaDevs, containsInAnyOrder(personService
                .getAllDevelopers()));

    }

}