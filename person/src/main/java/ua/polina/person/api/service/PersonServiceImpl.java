package ua.polina.person.api.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polina.person.api.PersonDto;
import ua.polina.person.api.messaging.ResponseEvent;
import ua.polina.person.entitty.Person;
import ua.polina.person.repository.PersonRepository;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person savePerson(PersonDto personDto) {
        Person person = Person.builder()
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .middleName(personDto.getMiddleName())
                .passport(personDto.getPassport())
                .build();

        return personRepository.save(person);
    }
}
