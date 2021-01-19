package ua.polina.person.api.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.polina.main_service.PersonDto;
import ua.polina.message_api.command.Command;

@Data
@AllArgsConstructor
public class CreateCommand implements Command<PersonDto> {
    public static final String QUEUE = "person3";

    private PersonDto personDto;

    @Override
    public PersonDto payload() {
        return personDto;
    }

    @Override
    public String queue() {
        return QUEUE;
    }

    public CreateCommand() {
    }
}

