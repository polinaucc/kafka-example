package ua.polina.person.api.service;

import ua.polina.person.api.PersonDto;
import ua.polina.person.entitty.Person;

public interface PersonService {
    Person savePerson(PersonDto personDto);
}
