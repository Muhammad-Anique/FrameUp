package com.example.FrameUpServer;

import com.example.FrameUpServer.GlobalFunctions.GlobalFunctions;
import com.example.FrameUpServer.Model.Person.Person;
import com.example.FrameUpServer.Model.Person.PersonDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

public class PersonTests {
    @Autowired
    private PersonDao personDao;


   // @Test
    void personSavingTest() {
        Person person = new Person();
        person.set_Name_Email_Roll("Alishba","l202121@lhr.nu.edu.pk","20l-2121");
        personDao.savePerson(person);
    }

    @Test
    void getally()
    {
        List<Person> P = personDao.getAllPersons();
        GlobalFunctions gb = new GlobalFunctions();
        gb.PrintStart();
        System.out.println(P);
        gb.PrintEnd();
    }

    //@Test
    void FetchingPersonByRollTest()
    {
        GlobalFunctions fg =new GlobalFunctions();
        Person p = personDao.getPersonByRoll("20l-2120");
        fg.PrintStart();
        System.out.println(p);
        fg.PrintEnd();
    }

    @Test
    void GetAllPersons()
    {
        List<Person> p = personDao.getAllPersons();
        System.out.println(p);
   }
}
