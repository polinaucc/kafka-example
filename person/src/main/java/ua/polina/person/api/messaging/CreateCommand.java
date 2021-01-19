package ua.polina.person.api.messaging;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.polina.message_api.command.Command;
import ua.polina.person.api.PersonDto;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCommand implements Command<PersonDto>, Serializable {
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
}
