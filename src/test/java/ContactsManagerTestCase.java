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
        var contact = new Contact("foo", "917 252 063");
        cm.addContact(contact);

        assertEquals(1, cm.size());
        assertSame(contact, cm.getContacts().get(0));
    }

    @Test
    public void testDontAllowDuplicatedContacts() {
        var contact1 = new Contact("foo", "931 456 907");
        var contact2 = new Contact("foo", "931 456 907");

        cm.addContact(contact1);
        cm.addContact(contact2);

        assertEquals(1, cm.size(), "Duplicated contacts!");
    }

    @Test
    public void testRemoveContact() {
        var contact = new Contact("foo", "931 456 907");

        cm.addContact(contact);
        assertEquals(1, cm.size());

        cm.removeContact(contact);
        assertTrue(cm.isEmpty());
    }

    @Test
    public void testTryRemoveNonexistentContact() {
        var foobar = new Contact("Foo", "Bar", "928 032 179", "foo@bar.test");
        var dummy = new Contact("Mr.", "Dummy","964 475 145", "mr@dummy.test");

        cm.addContact(foobar);
        assertEquals(1, cm.size());

        cm.removeContact(dummy);
        assertFalse(cm.isEmpty());
    }

    @Test
    public void testDontRemoveUnlessIsSameContact() {
        var foo = new Contact("foo", "91X ABC DEF");
        var bar = new Contact("bar", "91X ABC DEF");

        cm.addContact(foo);
        assertEquals(1, cm.size());

        cm.removeContact(bar);
        assertFalse(cm.isEmpty());
    }


    @Test
    public void testGetContactsWithEmptyLabels() {
        var foo = new Contact("foo", "91Y YYY YYY");
        var bar = new Contact("bar", "91X XXX XXX");

        cm.addContact(foo);
        cm.addContact(bar);

        assertEquals(2, cm.size());
        assertEquals(2, cm.getContacts().size());
    }

    @Test
    public void testGetContactsWithLabels() {
        var foo = new Contact("foo", "91Y YYY YYY");

        cm.addContact(foo,  "ipl");

        assertEquals(1, cm.getContacts("ipl").size());
    }

    @Test
    public void testGetContactsWithDifferentLabels() {
        var foo = new Contact("foo", "91Y YYY YYY");

        cm.addContact(foo,  "ipl");

        assertEquals(0, cm.getContacts("ipl", "xgeeks").size());
    }



}
