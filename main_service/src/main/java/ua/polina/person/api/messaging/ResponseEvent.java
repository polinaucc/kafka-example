package ua.polina.person.api.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.main_service.dto.ResponseDto;
import ua.polina.message_api.command.Event;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEvent implements Event<ResponseDto> {
    private static final String topic = "response";

    private ResponseDto responseDto;

    @Override
    public ResponseDto payload() {
        return responseDto;
    }

    @Override
    public String topic() {
        return topic;
    }
}
