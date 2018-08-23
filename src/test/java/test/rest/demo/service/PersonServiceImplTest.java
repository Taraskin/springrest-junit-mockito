package test.rest.demo.service;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import test.rest.demo.entity.Person;
import test.rest.demo.entity.PersonStatus;
import test.rest.demo.exception.PersonNotFoundException;
import test.rest.demo.repository.PersonRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

    private static final long PERSON_ID = 1L;
    private static int testCounter = 0;

    private Person person;

    @Mock private PersonRepository personRepository;

    @InjectMocks private PersonService personService = new PersonServiceImpl();

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Just for a test purposes, @BeforeClass call");
    }

    @Before
    public void setUp() throws Exception {
        person = new Person("John Doe", 17);
        person.setId(PERSON_ID);
        System.out.println("@Before " + ++testCounter);
    }

    @Test
    public void getAll() throws Exception {
        personService.getAll();
        verify(personRepository, atLeast(1)).findAll();
    }

    @Test
    public void addOrUpdatePerson() throws Exception {
        personService.addOrUpdatePerson(person);
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void removePerson() throws Exception {
        personService.removePerson(PERSON_ID);
        verify(personRepository, times(1)).deleteById(eq(PERSON_ID));
    }

    @Test
    public void getById() throws Exception {
        System.out.println("@Test getById()");
        personService.getById(-1L);
        verify(personRepository, never()).findById(eq(1L));
        personService.getById(1L);
        verify(personRepository, never()).findById(eq(1L));
        personService.getById(99L);
        verify(personRepository, never()).findById(eq(1L));

        personService.getById(100L);
        verify(personRepository, times(1)).findById(eq(100L));
    }

    @Test(expected = PersonNotFoundException.class)
    public void determinePersonStatus_wrong_input() throws Exception {
        when(personRepository.findById(anyLong())).thenThrow(PersonNotFoundException.class);
        personService.determinePersonStatus(1L);
    }

    @Test
    public void determinePersonStatus_no_data_found() throws Exception {
        when(personRepository.findById(PERSON_ID)).thenReturn(Optional.empty());
        Assert.assertEquals(PersonStatus.UNDEFINED, personService.determinePersonStatus(PERSON_ID));
    }

    @Test
    public void determinePersonStatus() throws Exception {
        Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(Optional.of(person));
        Assert.assertEquals(PersonStatus.TEENAGER, personService.determinePersonStatus(PERSON_ID));

        person.setAge(10);
        Assert.assertEquals(PersonStatus.BABY, personService.determinePersonStatus(PERSON_ID));

        person.setAge(23);
        Assert.assertEquals(PersonStatus.YOUNG, personService.determinePersonStatus(PERSON_ID));

        person.setAge(45);
        Assert.assertEquals(PersonStatus.ADULT, personService.determinePersonStatus(PERSON_ID));

        person.setAge(62);
        Assert.assertEquals(PersonStatus.RETIRED, personService.determinePersonStatus(PERSON_ID));

        verify(personRepository, times(5)).findById(eq(PERSON_ID));
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("Just for a test purposes, @AfterClass call");
    }

}