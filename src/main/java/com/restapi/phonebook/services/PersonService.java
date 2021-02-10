package com.restapi.phonebook.services;

import com.restapi.phonebook.entities.City;
import com.restapi.phonebook.entities.Person;
import com.restapi.phonebook.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if(!validateEmail(person.getEmail())){
            throw new InvalidParameterException("invalid email!!");
        }

        Optional<Person> personOptional = personRepository
                .findPersonByEmail(person.getEmail());

        if(personOptional.isPresent()){
            throw new IllegalStateException("person already exists!!");
        }

        personRepository.save(person);
    }

    @Transactional
    public void updatePerson(Long personId, String name, String middleName, String lastName, String email, City city) {

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

        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(person.getEmail(), name)){
            person.setEmail(email);
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

    private boolean validateEmail(String email){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public List<Person> getPersonsFromCity(String cityName, String cityRegion) {
        return personRepository.findAllFromCity(cityName, cityRegion);
    }
}
