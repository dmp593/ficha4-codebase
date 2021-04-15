package pt.ipleiria.estg.dei.esoft.managers;

import pt.ipleiria.estg.dei.esoft.models.Contact;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ContactsManager {
    private List<Contact> contacts;
    private HashMap<String, List<Contact>> labels;

    public ContactsManager() {
        this.contacts = new LinkedList<>();
        this.labels = new HashMap<>(200);
    }

    public List<String> getLabels() {
        // TODO return all the labels
    }

    public List<Contact> getContacts(String... labels) {
        // TODO get contacts in labels or all ...
    }

    public List<Contact> search(String term, String... labels) {
        // TODO search for contacts with term, and also in specific labels
    }

    public void addContact(Contact contact, String... labels) {
        // TODO add contact and associate it with the labels, if any
        // DO NOT ALLOW TO ADD DUPLICATED CONTACTS (same phone and/or email)
    }

    public void removeContact(Contact contact) {
        // TODO remove the contact
    }

    public int size() {
        return contacts.size();
    }
}
