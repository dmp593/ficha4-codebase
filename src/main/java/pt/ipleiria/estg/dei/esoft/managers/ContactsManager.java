package pt.ipleiria.estg.dei.esoft.managers;

import pt.ipleiria.estg.dei.esoft.models.Contact;

import java.util.*;
import java.util.stream.Collectors;

public class ContactsManager {
    private List<Contact> contacts;
    private HashMap<String, List<Contact>> labels;

    public ContactsManager() {
        this.contacts = new LinkedList<>();
        this.labels = new HashMap<>(200);
    }

    public List<String> getLabels() {
        return new ArrayList<>(labels.keySet());
    }

    public void addLabel(String label) {
        this.labels.put(label.toLowerCase(), new LinkedList<>());
    }

    public void removeLabel(String label) {
        this.labels.remove(label.toLowerCase());
    }

    public List<Contact> getContacts(String... labels) {
        if (labels.length == 0) return contacts;

        var contactsFound = new LinkedList<Contact>();

        for (var contact : contacts) {
            var isContactPresentInAllLabels = true;

            for (var label : labels) {
                var labelLowerCase = label.toLowerCase();
                if (! this.labels.containsKey(labelLowerCase)) {
                    isContactPresentInAllLabels = false;
                    break;
                }

                var contactsLabel = this.labels.get(labelLowerCase);

                if (! contactsLabel.contains(contact)) {
                    isContactPresentInAllLabels = false;
                    break;
                }
            }

            if (isContactPresentInAllLabels) {
                contactsFound.add(contact);
            }
        }

        return contactsFound;
    }

    public List<Contact> search(String term, String... labels) {
        if (labels.length == 0 && term.trim().isBlank()) return contacts;

        List<String> searchingLabels;

        if (labels.length == 0) {
            searchingLabels = this.getLabels();
        } else {
            searchingLabels = Arrays.stream(labels).collect(Collectors.toList());
        }

        var contactsFound = new LinkedList<Contact>();

        for (var label : searchingLabels) {
            var labelLowerCase = label.toLowerCase();

            if (! this.labels.containsKey(labelLowerCase)) continue;

            var contacts = this.labels.get(labelLowerCase);
            for (var contact : contacts) {
                if (contact.matches(term) && !contactsFound.contains(contact)) {
                    contactsFound.add(contact);
                }
            }
        }

        return contactsFound;
    }

    public void addContact(Contact contact, String... labels) {
        // do not allow duplicated contacts (same phone and/or email)
        if (!contacts.contains(contact)) contacts.add(contact);

        if (labels.length == 0) return;

        for (var label : labels) {
            var labelLowerCase = label.toLowerCase();

            if (!this.labels.containsKey(labelLowerCase)) {
                this.addLabel(labelLowerCase);
            }

            var contactsLabel = this.labels.get(labelLowerCase);
            if (!contactsLabel.contains(contact)) {
                contactsLabel.add(contact);
            }
        }
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        labels.values().forEach(contacts -> contacts.remove(contact));
    }

    public int size() {
        return contacts.size();
    }

    public boolean isEmpty() {
        return contacts.isEmpty();
    }
}
