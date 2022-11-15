package com.example.FrameUpServer;

import com.example.FrameUpServer.GlobalFunctions.GlobalFunctions;
import com.example.FrameUpServer.Model.Contact.Contact;
import com.example.FrameUpServer.Model.Contact.ContactDao;
import com.example.FrameUpServer.Model.Message.Message;
import com.example.FrameUpServer.Model.Message.MessageDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ContactTests {

    @Autowired
    private ContactDao contactDao;

    @Test
    void contactSavingTest() {
        Contact contact = new Contact();
        contact.setContactData(1, "l200917", "l200928");
        contactDao.saveContacts(contact);
    }

    //@Test
    void getally()
    {
        List<Contact> C = contactDao.getAllContacts();
        GlobalFunctions gb = new GlobalFunctions();
        gb.PrintStart();
        System.out.println(C);
        gb.PrintEnd();
    }

    //@Test
    void GetAllContacts()
    {
        List<Contact> C = contactDao.getAllContacts();
        System.out.println(C);
    }
}
