package springcourse.stepanov.springcourse.stepanov.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springcourse.stepanov.springcourse.stepanov.dto.PersonDTO;
import springcourse.stepanov.springcourse.stepanov.models.Person;
import springcourse.stepanov.springcourse.stepanov.services.PeopleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people_list")
public class PeopleController {
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDTO> getPeopleList() {
        return peopleService.getPeopleList()
                .stream()
                .map(p -> modelMapper.map(p, PersonDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id) {
        return modelMapper.map(peopleService.getPerson(id), PersonDTO.class);
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
    public PersonDTO getPersonByNameAge(@PathVariable("name") String name,
                                     @PathVariable("age") int age) {
        return modelMapper.map(peopleService.getPersonByNameAndAge(name, age), PersonDTO.class);
    }

    @GetMapping("/list/{age}")
    public List<PersonDTO> getPeopleListByAge(@PathVariable("age") int age) {
        return peopleService.getPeopleListByAge(age)
                .stream()
                .map(p -> modelMapper.map(p, PersonDTO.class))
                .collect(Collectors.toList());
    }
}