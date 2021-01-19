package ua.polina.main_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.polina.main_service.dto.PersonDto;
import ua.polina.main_service.dto.ResponseDto;
import ua.polina.main_service.service.PersonService;

import javax.validation.Valid;

@RestController
public class PersonController {
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseDto savePerson(@Valid @RequestBody PersonDto personDto){
        return service.savePerson(personDto);
    }
}
