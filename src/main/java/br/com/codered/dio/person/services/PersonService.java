package br.com.codered.dio.person.services;

import br.com.codered.dio.person.dto.mapper.PersonMapper;
import br.com.codered.dio.person.dto.request.PersonDTO;
import br.com.codered.dio.person.dto.response.MessageResponseDTO;
import br.com.codered.dio.person.entities.Person;
import br.com.codered.dio.person.exception.PersonNotFoundException;
import br.com.codered.dio.person.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public MessageResponseDTO create(PersonDTO personDTO) {
        var person = personMapper.toModel(personDTO);
        var savedPerson = personRepository.save(person);

        return createMessageResponse("Person successfully created with ID ", savedPerson.getId());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        var person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return personMapper.toDTO(person);
    }

    public List<PersonDTO> listAll() {
        List<Person> people = personRepository.findAll();
        return people.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        var updatedPerson = personMapper.toModel(personDTO);
        var savedPerson = personRepository.save(updatedPerson);

        MessageResponseDTO messageResponse = createMessageResponse("Person successfully updated with ID ", savedPerson.getId());

        return messageResponse;
    }

    public void delete(Long id) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        personRepository.deleteById(id);
    }

    private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }
}
