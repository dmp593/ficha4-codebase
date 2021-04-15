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

    @Test
    public void testDontAllowDuplicatedContacts() {
        var contact1 = new Contact("foo", "931 456 907");
        var contact2 = new Contact("foo", "931 456 907"); // same contact!

        cm.addContact(contact1);
        cm.addContact(contact2);

        // assertSame(contact1, contact2);

        // Help the developer by writing a friendly message :)
        assertEquals(1, cm.size(), "Duplicated contacts!");
    }

    @Test
    public void testDontAllowDuplicatedPhones() {
        var contact1 = new Contact("foo", "931 456 907");
        var contact2 = new Contact("bar", "931 456 907");

        cm.addContact(contact1);
        cm.addContact(contact2);

        assertEquals(1, cm.size(), "Duplicated phones!");
    }

    @Test
    public void testRemoveContact() {
        var contact = new Contact("foo", "931 456 907");

        cm.addContact(contact); assertEquals(1, cm.size());
        cm.removeContact(contact);

        assertTrue(cm.isEmpty(), "<give a meaningful message>");
    }

    @Test
    public void testTryRemoveNonExistentContact() {
        var foobar = new Contact("Foo", "Bar", "928 032 179", "foo@bar.test");
        var dummy = new Contact("Mr.", "Dummy","964 475 145", "mr@dummy.test");

        cm.addContact(foobar);
        assertEquals(1, cm.size());

        cm.removeContact(dummy);
        assertFalse(cm.isEmpty(), "<give a meaningful message>");
    }

    @Test
    public void testDontRemoveUnlessIsSameContact() {
        var foo = new Contact("foo", "91X ABC DEF");
        var bar = new Contact("bar", "91X ABC DEF");

        cm.addContact(foo);
        assertEquals(1, cm.size());

        cm.removeContact(bar);
        assertFalse(cm.isEmpty(), "<give a meaningful message>");
    }

    @Test
    public void testGetAllLabels() {
        fail("write a test that tests getting all labels");
    }

    @Test
    public void testGetAllContacts() {
        fail("write a test that tests getting all contacts");
    }

    @Test
    public void testGetContactUsingOneLabel() {
        fail("write a test that tests getting contacts by label");
    }

    @Test
    public void testGetContactUsingMultipleLabels() {
        fail("write a test that tests getting contacts by multiple labels");
    }

    @Test
    public void testSearchContactsUsingOneLabel() {
        fail("write a test that tests search contacts by label");
    }

    @Test
    public void testSearchContactsUsingMultipleLabels() {
        fail("write a test that tests search contacts by labels");
    }

    @Test
    public void testSearchContactsUsingOneLabelAndSearchTerm() {
        fail("write a test that tests search contacts by label and term");
    }

    @Test
    public void testSearchContactsUsingMultipleLabelsAndSearchTerm() {
        fail("write a test that tests search contacts by labels and term");
    }

    // TODO implement other tests that you may consider important
}
