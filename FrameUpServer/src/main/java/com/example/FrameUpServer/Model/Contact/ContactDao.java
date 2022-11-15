package com.example.FrameUpServer.Model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactDao {

    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContacts(Contact contacts){
        return contactRepository.save(contacts);
    }

    public List<Contact> getAllContacts()
    {
        List<Contact> contacts = new ArrayList<>();
        Streamable.of(contactRepository.findAll())
                .forEach(contacts::add);
        return contacts;
    }

}
