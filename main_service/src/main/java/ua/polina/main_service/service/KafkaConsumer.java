package ua.polina.main_service.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ua.polina.main_service.ResponseDto;
import ua.polina.person.api.messaging.ResponseEvent;

@Slf4j
@Getter
@Setter
@Service
@KafkaListener(topics = "response", groupId = "response1", containerFactory = "responseDtoKafkaListenerFactory")
public class KafkaConsumer {

    private ResponseDto response;

    @KafkaHandler
    public void listenGroupResponse(ResponseEvent event){
        log.info("Getting response: '{0}' " + event);
        try{
            ResponseDto responseDto = event.payload();
            response = responseDto;
            log.info("Success process of response: " + responseDto);
        }
        catch (Exception e){
            log.info("An error occurred: " + e.getMessage());
        }
    }
}
