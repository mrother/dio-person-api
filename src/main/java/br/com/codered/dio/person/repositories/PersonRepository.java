package br.com.codered.dio.person.repositories;

import br.com.codered.dio.person.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
