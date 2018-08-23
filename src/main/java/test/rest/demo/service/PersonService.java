package test.rest.demo.service;

import test.rest.demo.entity.Person;
import test.rest.demo.entity.PersonStatus;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> getAll();

    Person addOrUpdatePerson(Person person);

    void removePerson(Long id);

    Optional<Person> getById(Long id);

    PersonStatus determinePersonStatus(Long id);
}
