package springcourse.stepanov.springcourse.stepanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springcourse.stepanov.springcourse.stepanov.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}