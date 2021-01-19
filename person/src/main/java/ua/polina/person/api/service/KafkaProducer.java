package ua.polina.person.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ua.polina.person.api.ResponseDto;
import ua.polina.person.api.messaging.ResponseEvent;

@Service
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, ResponseEvent> responseDtoKafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, ResponseEvent> responseDtoKafkaTemplate) {
        this.responseDtoKafkaTemplate = responseDtoKafkaTemplate;
    }

    public void sendMessage(ResponseDto dto){
        log.info("Sending response: " + dto);
        ResponseEvent event = new ResponseEvent(dto);
        responseDtoKafkaTemplate.send(event.topic(), event);
    }
}
