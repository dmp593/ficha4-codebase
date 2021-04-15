package pt.ipleiria.estg.dei.esoft.tests;

import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.esoft.models.Contact;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTestCase {

    @Test
    public void testCreateEmptyContact() {
        var contact = new Contact();

        assertNull(contact.getFirstName());
        assertNull(contact.getLastName());
        assertNull(contact.getBirthday());
        assertNull(contact.getPhone());
        assertNull(contact.getEmail());
    }

    @Test
    public void testCreateContactWithFirstNameAndLastName() {
        var contact = new Contact("foo", "912 345 678");

        assertEquals("foo", contact.getFirstName());
        assertEquals("912 345 678", contact.getPhone());

        assertNull(contact.getLastName());
        assertNull(contact.getEmail());
        assertNull(contact.getBirthday());
    }

    @Test
    public void testCreateContactWithFirstNameLastNameAndPhone() {
        var contact = new Contact("foo", "bar", "912 345 678");

        assertEquals("foo", contact.getFirstName());
        assertEquals("bar", contact.getLastName());
        assertEquals("912 345 678", contact.getPhone());

        assertNull(contact.getEmail());
        assertNull(contact.getBirthday());
    }

    @Test
    public void testCreateContactWithFirstNameLastNamePhoneAndEmail() {
        var contact = new Contact("foo", "bar", "912 345 678", "foo@bar.dev");

        assertEquals("foo", contact.getFirstName());
        assertEquals("bar", contact.getLastName());
        assertEquals("912 345 678", contact.getPhone());
        assertEquals("foo@bar.dev", contact.getEmail());

        assertNull(contact.getBirthday());
    }

    @Test
    public void testCreateFullContact() {
        var calendar = GregorianCalendar.getInstance();

        calendar.set(GregorianCalendar.DAY_OF_MONTH, 10);
        calendar.set(GregorianCalendar.MONTH, 3);
        calendar.set(GregorianCalendar.YEAR, 1995);

        var contact = new Contact("foo", "bar", "912 345 678", "foo@bar.dev", calendar.getTime());

        assertEquals("foo", contact.getFirstName());
        assertEquals("bar", contact.getLastName());
        assertEquals("912 345 678", contact.getPhone());
        assertEquals("foo@bar.dev", contact.getEmail());
        assertEquals(calendar.getTime(), contact.getBirthday());
    }

    // TODO implement other tests that you may consider important
}
