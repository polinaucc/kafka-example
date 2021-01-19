package ua.polina.main_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ua.polina.main_service.PersonDto;
import ua.polina.main_service.ResponseDto;
import ua.polina.person.api.messaging.CreateCommand;

@Service
@Slf4j
public class PersonService {
    private final KafkaTemplate<String, CreateCommand> personDtoKafkaTemplate;
    private final KafkaConsumer kafkaConsumer;


    @Autowired
    public PersonService(KafkaTemplate<String, CreateCommand> personDtoKafkaTemplate,
                         KafkaConsumer kafkaConsumer) {
        this.personDtoKafkaTemplate = personDtoKafkaTemplate;
        this.kafkaConsumer = kafkaConsumer;
    }

    /**
     * Sends person data, waits for response and returns it.
     *
     * @return response
     */
    public ResponseDto savePerson(PersonDto dto) {
        log.info("Sending dto: " + dto);
        CreateCommand createCommand = new CreateCommand();
        createCommand.setPersonDto(dto);
        personDtoKafkaTemplate.send(createCommand.queue(), createCommand);
        return getResponse();
    }

    /**
     * Checks if the response has already been getting. If there is no answer for a long time,
     * it generates an unsuccessful response.
     *
     * @return response
     */
    private ResponseDto getResponse() {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 2*60*1000;
        while (System.currentTimeMillis()<endTime){
            if(kafkaConsumer.getResponse()!=null){
                return kafkaConsumer.getResponse();
            }
        }
        kafkaConsumer.setResponse(null);
        return new ResponseDto(false, "Something went wrong!");
    }


}
