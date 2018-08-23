package test.rest.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.rest.demo.entity.Person;
import test.rest.demo.entity.PersonStatus;
import test.rest.demo.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person addOrUpdatePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void removePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Optional<Person> getById(Long id) {
        if (id < 100L) {
            System.out.println("Access denied! ID = " + id);
            return Optional.empty();
        } else {
            System.out.println("Success! Get person by ID = " + id);
            return personRepository.findById(id);
        }
    }

    @Override
    public PersonStatus determinePersonStatus(Long id) {
        return PersonStatus.determine(personRepository.findById(id).map(Person::getAge).orElse(0));
    }
}
