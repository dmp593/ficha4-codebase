package pt.ipleiria.estg.dei.esoft.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.esoft.managers.ContactsManager;
import pt.ipleiria.estg.dei.esoft.models.Contact;

import static org.junit.jupiter.api.Assertions.*;

public class ContactsManagerTestCase {

    private ContactsManager cm;

    @BeforeEach
    public void setUp() {
        cm = new ContactsManager();
    }

    @Test
    public void testAddContact() {
        var contact = new Contact("foo", "912 345 678");

        cm.addContact(contact);

        assertEquals(1, cm.size());
        assertSame(contact, cm.getContacts().get(0));
    }
}
