package ru.job4j.auth.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.service.PersonService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.ArgumentCaptor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Класс PersonControllerTest
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:schema.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Autowired
    private Gson gson;

    @Test
    public void findAll() throws Exception {
        List<Person> persons = List.of(Person.of(0, "Ivan", "123"));
        when(personService.findAll()).thenReturn(persons);
        this.mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(personService).findAll();
        assertThat(personService.findAll(), is(persons));
    }

    @Test
    public void whenNotFoundById() throws Exception {
        this.mockMvc.perform(get("/person/0"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenFoundById() throws Exception {
        Person person = Person.of(0, "Ivan", "123");
        when(personService.findById(0)).thenReturn(Optional.of(person));
        String json = gson.toJson(person);
        this.mockMvc.perform(post("/person/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
        this.mockMvc.perform(get("/person/0"))
                .andDo(print())
                .andExpect(status().isOk());
        ArgumentCaptor<Person> personArgument = ArgumentCaptor.forClass(Person.class);
        verify(personService).save(personArgument.capture());
        assertThat(personArgument.getValue().getLogin(), is("Ivan"));
        assertThat(personArgument.getValue().getPassword(), is("123"));
    }

    @Test
    public void create() throws Exception {
        Person person = Person.of(0, "Ivan", "123");
        String json = gson.toJson(person);
        this.mockMvc.perform(post("/person/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
        ArgumentCaptor<Person> personArgument = ArgumentCaptor.forClass(Person.class);
        verify(personService).save(personArgument.capture());
        assertThat(personArgument.getValue().getLogin(), is("Ivan"));
        assertThat(personArgument.getValue().getPassword(), is("123"));
    }

    @Test
    public void whenUpdate() throws Exception {
        Person person = Person.of(0, "Ivan", "123");
        String json = gson.toJson(person);
        this.mockMvc.perform(post("/person/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
        person.setPassword("ChangedPassword");
        String updatedJson = gson.toJson(person);
        this.mockMvc.perform(put("/person/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenDelete() throws Exception {
        Person person = Person.of(0, "Ivan", "123");
        String json = gson.toJson(person);
        this.mockMvc.perform(post("/person/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
        this.mockMvc.perform(delete("/person/0"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}