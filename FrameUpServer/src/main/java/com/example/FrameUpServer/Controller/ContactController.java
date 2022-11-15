package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.Contact.Contact;
import com.example.FrameUpServer.Model.Contact.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class ContactController {

    @Autowired
    private ContactDao contactDao;

    @GetMapping("/contact/get-all")
    public List<Contact> getAllContacts()
    {
        return contactDao.getAllContacts();
    }
    @PostMapping("/contact/save")
    public Contact save(@RequestBody Contact C)
    {
        return contactDao.saveContacts(C);
    }
}
