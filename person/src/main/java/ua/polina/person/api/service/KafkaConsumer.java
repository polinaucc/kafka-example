package ua.polina.person.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ua.polina.person.api.PersonDto;
import ua.polina.person.api.ResponseDto;
import ua.polina.person.api.messaging.CreateCommand;
import ua.polina.person.entitty.Person;

@Slf4j
@Service
@KafkaListener(topics = "person3", groupId = "person", containerFactory = "personDtoConcurrentKafkaListenerContainerFactory")
public class KafkaConsumer {
    private final PersonService personService;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public KafkaConsumer(PersonService personService, KafkaProducer kafkaProducer) {
        this.personService = personService;
        this.kafkaProducer = kafkaProducer;
    }

    @KafkaHandler
    public void listenGroupPerson(CreateCommand command) {
        log.info("Sending data: ' " + command);
        try {
            PersonDto personDto = command.payload();
            Person person = personService.savePerson(personDto);
            log.info("Person successfully saved: " + person);
            kafkaProducer.sendMessage(new ResponseDto(true, person.toString()));
        } catch (Exception e) {
            kafkaProducer.sendMessage(new ResponseDto(false, "The person isn't saved"));
        }
    }
}
