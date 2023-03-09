package springcourse.stepanov.springcourse.stepanov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcourse.stepanov.springcourse.stepanov.models.Person;
import springcourse.stepanov.springcourse.stepanov.repository.PeopleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getPeopleList() {
        return peopleRepository.findAll();
    }

    public Person getPerson(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addPerson(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void editPerson(int id, Person person) {
        Person willBeUpdated = getPerson(id);
        willBeUpdated.setName(person.getName());
        willBeUpdated.setAge(person.getAge());

        peopleRepository.save(willBeUpdated);
    }

    @Transactional
    public void deletePerson(int id) {
        Person person = getPerson(id);
        peopleRepository.delete(person);
    }

    public Person getPersonByNameAndAge(String name, int age) {
        return getPeopleList().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name) & p.getAge() == age)
                .findAny().orElse(null);
    }

    public List<Person> getPeopleListByAge(int age) {
        return getPeopleList().stream()
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }
}