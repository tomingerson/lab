package de.fh_kiel.person.rest;

import de.fh_kiel.ApplicationConfig;
import de.fh_kiel.person.Gender;
import de.fh_kiel.person.Person;
import de.fh_kiel.person.PersonService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Created by tom on 13.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@WebAppConfiguration
public class PersonControllerTest {

    private MockMvc mockMvc;
    private Person testPerson1;
    private Person testPerson2;


    private HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PersonService personService;

    @Autowired
    void setConverters(HttpMessageConverter<Object>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElseThrow(() -> new AssertionError("the JSON message converter must not be null"));
    }

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        testPerson1 = new Person(null, "testFN1", "testLN1", LocalDate.of(1990, 1, 1), Gender
                .OTHER);
        personService.createPerson(testPerson1);

        testPerson2 = new Person(null, "testFN2", "testLN2", LocalDate.of(1990, 2, 2), Gender
                .FEMALE);
        personService.createPerson(testPerson2);
    }

    @After
    public void teardown() {
        personService.deletePerson(testPerson2);
        personService.deletePerson(testPerson1);
        this.mockMvc = null;
    }

    /**
     * Converts an object to a string in JSON-format using the wired {@link HttpMessageConverter}.
     *
     * @param o this object is converted to JSON
     * @return JSON-formatted object
     * @throws IOException in case the Jackson-converter fails
     */
    private String json(Object o) throws IOException {
        final MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        if (this.mappingJackson2HttpMessageConverter.canWrite(o.getClass(), MediaType
                .APPLICATION_JSON_UTF8)) {
            this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON_UTF8,
                    mockHttpOutputMessage);
        }
        return mockHttpOutputMessage.getBodyAsString();
    }

    @Test
    public void createPerson() throws Exception {
        final Person person = new Person(null, "postFN", "postLN", LocalDate.of(1999, 9, 9), Gender
                .MALE);
        final String jsonPerson = json(person);
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonPerson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void createPersonError() throws Exception {
        final Person error = new Person(99L, "errorFN", "errorLN", LocalDate.MAX, Gender.OTHER);
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json(error)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getPerson() throws Exception {
        mockMvc.perform(get("/person/" + testPerson1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(testPerson1.getId().intValue())));
    }

    @Test
    public void getPersonError() throws Exception {
        mockMvc.perform(get("/person/23")).andExpect(status().isNotFound());
    }

    @Test
    public void getAllPersons() throws Exception {
        mockMvc.perform(get("/person"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(testPerson1.getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(testPerson1.getFirstName())))
                .andExpect(jsonPath("$[1].id", is(testPerson2.getId().intValue())))
                .andExpect(jsonPath("$[1].lastName", is(testPerson2.getLastName())));
    }
}