package pt.ipleiria.estg.dei.esoft.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String phone;
    @Getter @Setter private String email;
    @Getter @Setter private LocalDate birthday;

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

    public boolean matches(String term) {
        var termLowerCase = term.toLowerCase();
        if (termLowerCase.isBlank()) return false;

        if (this.firstName != null && this.firstName.toLowerCase().contains(term)) return true;
        if (this.lastName != null && this.lastName.toLowerCase().contains(term)) return true;
        if (this.phone != null && this.phone.toLowerCase().contains(term)) return true;
        if (this.email != null && this.email.toLowerCase().contains(term)) return true;
        if (this.birthday != null && this.birthday.toString().contains(term)) return true;

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday, phone, email);
    }
}
