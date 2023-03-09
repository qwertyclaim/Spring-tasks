package springcourse.stepanov.springcourse.stepanov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springcourse.stepanov.springcourse.stepanov.models.Person;
import springcourse.stepanov.springcourse.stepanov.services.PeopleService;

import java.util.List;

@RestController
@RequestMapping("/people_list")
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<Person> getPeopleList() {
        return peopleService.getPeopleList();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return peopleService.getPerson(id);
    }

    @PostMapping()
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        peopleService.addPerson(person);
        return new ResponseEntity<>("Человек успешно добавлен!", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> editPerson(@PathVariable("id") int id, @RequestBody Person person) {
        peopleService.editPerson(id, person);
        return new ResponseEntity<>("Человек был успешно изменён", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") int id) {
        peopleService.deletePerson(id);
        return new ResponseEntity<>("Человек успешно удалён!", HttpStatus.ACCEPTED);
    }

    @GetMapping("/{name}/{age}")
    public Person getPersonByNameAge(@PathVariable("name") String name,
                                     @PathVariable("age") int age) {
        return peopleService.getPersonByNameAndAge(name, age);
    }

    @GetMapping("/list/{age}")
    public List<Person> getPeopleListByAge(@PathVariable("age") int age) {
        return peopleService.getPeopleListByAge(age);
    }
}