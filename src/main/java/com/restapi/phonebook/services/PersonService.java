package com.restapi.phonebook.services;

import com.restapi.phonebook.entities.City;
import com.restapi.phonebook.entities.Person;
import com.restapi.phonebook.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {

    private final IPersonRepository personRepository;

    @Autowired
    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    public Person getPersonById(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new IllegalStateException(
                        "person with id " + personId + " does not exists"
                ));
    }

    public void addPerson(Person person){
        Optional<Person> personOptional = personRepository
                .findPersonByEGN(person.getEgn());

        if(personOptional.isPresent()){
            throw new IllegalStateException("person already exists!!");
        }

        personRepository.save(person);
    }

    @Transactional
    public void updatePerson(Long personId, String name, String middleName, String lastName, String egn, City city) {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalStateException(
                        "person with id: " + personId + " doesn't exist!!"
                ));

        if(name != null &&
                name.length() > 0 &&
                !Objects.equals(person.getName(), name)){
            person.setName(name);
        }

        if(middleName != null &&
                middleName.length() > 0 &&
                !Objects.equals(person.getMiddleName(), name)){
            person.setMiddleName(middleName);
        }

        if(lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(person.getLastName(), name)){
            person.setLastName(lastName);
        }

        if(egn != null &&
                egn.length() > 0 &&
                !Objects.equals(person.getEgn(), name)){
            person.setEgn(egn);
        }

        if(city != null &&
            !Objects.equals(person.getCity(), city)){
            person.setCity(city);
        }
    }

    public void deletePerson(Long personId) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new IllegalStateException(
                "person with id: " + personId + " doesn't exist!!"
        ));

        personRepository.delete(person);
    }
}
