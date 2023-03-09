package springcourse.stepanov.springcourse.stepanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springcourse.stepanov.springcourse.stepanov.models.Person;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    @Query(value = "SELECT * FROM Person WHERE age > 30", nativeQuery = true)
    public List<Person> personList(int age);

    @Query(value = "SELECT * FROM Person WHERE name = ? AND age = ?", nativeQuery = true)
    public Person getPersonByNameAndAge(String name, int age);
}