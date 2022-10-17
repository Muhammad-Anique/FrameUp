package com.example.FrameUpServer.Controller;
import com.example.FrameUpServer.Model.Person.Person;
import com.example.FrameUpServer.Model.Person.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonDao personDao;

    @GetMapping("/person/get-all")
    public List<Person> getAllPersons()
    {
        return personDao.getAllPersons();
    }
    @PostMapping("/person/save")
    public Person save(@RequestBody Person P)
    {
        return personDao.savePerson(P);
    }
    @GetMapping("/person/{roll}")
    public Person getPersonByRollNo(@PathVariable String roll)
    {
        return personDao.getPersonByRoll(roll);
       //For Int Path variables (Integer.parseInt(id));
    }
}
