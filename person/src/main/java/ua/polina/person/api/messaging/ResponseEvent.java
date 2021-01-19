package ua.polina.person.api.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.message_api.command.Event;
import ua.polina.person.api.ResponseDto;

@Data
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

    public ResponseEvent() {
    }

    public ResponseEvent(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }
}
