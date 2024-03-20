package pt.ipleiria.estg.dei.esoft.managers;

import pt.ipleiria.estg.dei.esoft.models.Contact;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContactsManager {
    private List<Contact> contacts;
    private HashMap<String, List<Contact>> labels;

    public ContactsManager() {
        this.contacts = new LinkedList<>();
        this.labels = new HashMap<>(200);
    }

    public List<String> getLabels() {
        // return this.labels.keySet().stream().collect(Collectors.toList());
        return this.labels.keySet().stream().toList();
    }

    public List<Contact> getContacts(String... labels) {
        if (labels.length == 0) {
            return this.contacts;
        }

        var validLabels = Arrays.stream(labels).toList()
                .stream().filter(this.labels::containsKey).toList();

        List<Contact> contacts = new LinkedList<>();

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
        var contacts = getContacts(labels);
        
        if (term == null) {
            return contacts;
        }

        if (labels.length == 0) {
            labels = (String[]) this.labels.keySet().toArray();
        }

        var matches = new LinkedList<Contact>();

        for (var contact : contacts) {
            if (contact.match(term)) {
                matches.add(contact);
            }
        }

        return matches;
    }

    public void addContact(Contact contact, String... labels) {
        Predicate<Contact> isDuplicated = c ->
                Objects.equals(c.getPhone(), contact.getPhone()) && Objects.equals(c.getEmail(), contact.getEmail());

        if (this.contacts.stream().noneMatch(isDuplicated)) {
            this.contacts.add(contact);
        }

        for (var label : labels) {
            if (! this.labels.containsKey(label)) {
                this.labels.put(label, new LinkedList<>());
            }

            var contacts = this.labels.get(label);
            if (contacts.stream().noneMatch(isDuplicated)) {
                contacts.add(contact);
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
