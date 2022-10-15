package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.Person.Person;
import com.example.FrameUpServer.Model.Person.PersonDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class PersonTests {
    @Autowired
    private PersonDao personDao;


    @Test
    void personSavingTest() {
        Person person = new Person();
        person.set_Name_Email_Roll("Alishba","l202121@lhr.nu.edu.pk","20l-2121");
        personDao.savePerson(person);
    }
}
