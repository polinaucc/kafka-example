package ua.polina.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.person.entitty.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
