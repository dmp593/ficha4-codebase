package pt.ipleiria.estg.dei.esoft.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String phone;
    @Getter @Setter private String email;
    @Getter @Setter private Date birthday;

    public Contact(String firstName, String phone) {
        this.firstName = firstName;
        this.phone = phone;
    }

    public Contact(String firstName, String lastName, String phone) {
        this(firstName, phone);
        this.lastName = lastName;
    }

    public Contact(String firstName, String lastName, String phone, String email) {
        this(firstName, lastName, phone);
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday, phone, email);
    }
}
