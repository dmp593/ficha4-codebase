import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.esoft.models.Contact;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTestCase {
    @Test
    public void testCreateContact() {
        var contact = new Contact("foo", "919 229 773");

        assertEquals("foo", contact.getFirstName());
        assertEquals("919 229 773", contact.getPhone());
    }
}
