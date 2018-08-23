package test.rest.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.rest.demo.entity.Person;
import test.rest.demo.exception.PersonNotFoundException;
import test.rest.demo.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAll() {
        List<Person> people = personService.getAll();
        LOGGER.info("GET /api/persons/, found: {}", people.size());
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> get(@PathVariable Long id) throws PersonNotFoundException {
        Person person = personService.getById(id).orElseThrow(() -> new PersonNotFoundException(id));
        LOGGER.info("GET /api/persons/{}, found: {}", id, person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void addPerson(@RequestBody Person person) {
        Person s = personService.addOrUpdatePerson(person);
        LOGGER.info("POST /api/persons/, successfully added: {}", s);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void updatePerson(@RequestBody Person person) {
        Person s = personService.addOrUpdatePerson(person);
        LOGGER.info("PUT /api/persons/, successfully updated: {}", s);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void removePerson(@PathVariable Long id) {
        personService.removePerson(id);
        LOGGER.info("DELETE /api/persons/{} removed", id);
    }
}
