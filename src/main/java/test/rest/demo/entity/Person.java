package test.rest.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    private Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
