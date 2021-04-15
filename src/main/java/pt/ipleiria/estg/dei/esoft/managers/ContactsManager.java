package pt.ipleiria.estg.dei.esoft.managers;

import pt.ipleiria.estg.dei.esoft.models.Contact;

import java.util.ArrayList;
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

        return new ArrayList<>(labels.keySet());
    }

    public List<Contact> getContacts(String... labels) {
        // TODO get contacts in labels or all ...

        return contacts;
    }

    public List<Contact> search(String term, String... labels) {
        // TODO search for contacts with term, and also in specific labels

        return contacts;
    }

    public void addContact(Contact contact, String... labels) {
        // DO NOT ALLOW TO ADD DUPLICATED CONTACTS (same phone and/or email)
        if (!contacts.contains(contact)) contacts.add(contact);

        if (labels.length == 0) return;

        for (var label : labels) {
            if (!this.labels.containsKey(label)) {
                this.labels.put(label, new LinkedList<>());
            }

            var contactsLabel = this.labels.get(label);
            if (!contactsLabel.contains(contact)) {
                contactsLabel.add(contact);
            }
        }
    }

    public void removeContact(Contact contact) {
        // TODO remove the contact
    }

    public int size() {
        return contacts.size();
    }
}
