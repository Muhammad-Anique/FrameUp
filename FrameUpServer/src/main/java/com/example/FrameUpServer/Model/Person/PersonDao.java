package com.example.FrameUpServer.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonDao {

    @Autowired
    private PersonRepository personRepository;

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAllPersons()
    {
        List<Person> person = new ArrayList<>();
        Streamable.of(personRepository.findAll())
                .forEach(person::add);
        return person;
    }

    public Person getPersonByRoll(String roll)
    {
        return personRepository.retrievePersonByRoll_rp(roll);
    }


}
