package com.restapi.phonebook.controllers;

import com.restapi.phonebook.entities.City;
import com.restapi.phonebook.entities.Person;
import com.restapi.phonebook.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/persons")
public class PersonController {

    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPersons(){
        return personService.getPersons();
    }

    @GetMapping(path = "{personId}")
    public Person getPerson(@PathVariable("personId") Long personId){
        return personService.getPersonById(personId);
    }

    @PostMapping
    public void addPerson(@RequestBody Person person){
        try{
            personService.addPerson(person);
        }catch(IllegalStateException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Person already exists!!", e);
        }
    }

    @PutMapping(path = "{personId}")
    public void updatePerson(@PathVariable("personId") Long personId,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String middleName,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) String egn,
                             @RequestParam(required = false) City city){
        personService.updatePerson(personId, name, middleName, lastName, egn, city);
    }
}
