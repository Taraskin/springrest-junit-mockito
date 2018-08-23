package test.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.rest.demo.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
