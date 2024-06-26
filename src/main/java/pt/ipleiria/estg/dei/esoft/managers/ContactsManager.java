package pt.ipleiria.estg.dei.esoft.managers;

import pt.ipleiria.estg.dei.esoft.models.Contact;

import java.util.*;

public class ContactsManager {
    private List<Contact> contacts;
    private HashMap<String, List<Contact>> labels;

    public ContactsManager() {
        this.contacts = new LinkedList<>();
        this.labels = new HashMap<>(200);
    }

    public List<String> getLabels() {
        return this.labels.keySet().stream().toList();
    }

    public List<Contact> getContacts(String... labels) {
        if (labels.length == 0) {
            return this.contacts;
        }

        var contacts = new LinkedList<Contact>();
        var validLabels = Arrays.stream(labels).filter(this.labels::containsKey).toList();

        if (validLabels.size() != labels.length) {
            return contacts;
        }

        for (var contact : this.contacts) {
            var isPresentInAllLabels = true;

            for (var label : validLabels) {
                if (! this.labels.get(label).contains(contact)) {
                    isPresentInAllLabels = false;
                    break;
                }
            }

            if (isPresentInAllLabels) {
                contacts.add(contact);
            }
        }

        return contacts;
    }

    public List<Contact> search(String term, String... labels) {
        return getContacts(labels).stream().filter(c -> c.match(term)).toList();
    }

    public void addContact(Contact contact, String... labels) {
        if (this.contacts.stream().noneMatch(contact::isDuplicated)) {
            this.contacts.add(contact);
        }

        for (var label : labels) {
            if (! this.labels.containsKey(label)) {
                this.labels.put(label, new LinkedList<>());
            }

            var labelledContacts = this.labels.get(label);
            if (labelledContacts.stream().noneMatch(contact::isDuplicated)) {
                labelledContacts.add(contact);
            }
        }
    }

    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
        this.labels.values().forEach(contacts -> contacts.remove(contact));
    }

    public int size() {
        return contacts.size();
    }

    public boolean isEmpty() {
        return contacts.isEmpty();
    }
}
