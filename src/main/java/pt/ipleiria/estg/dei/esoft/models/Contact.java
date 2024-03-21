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

    public boolean isDuplicated(Contact another) {
        if (Objects.equals(this, another)) {
            // if it's the same instance, doesn't make sense the comparison.
            return false;
        }
        
        if (phone != null && phone.equals(another.phone)) {
            return true;
        }

        if (email != null && email.equals(another.email)) {
            return true;
        }

        return false;
    }

    // @Override
    // public boolean equals(Object o) {
    //     if (!(o instanceof Contact)) return false;

    //     var another = (Contact) o;

    //     if (! Objects.equals(this.firstName, another.firstName)) return false;
    //     if (! Objects.equals(this.lastName, another.lastName)) return false;
    //     if (! Objects.equals(this.birthday, another.birthday)) return false;
    //     if (! Objects.equals(this.phone, another.phone)) return false;
    //     if (! Objects.equals(this.email, another.email)) return false;

    //     return true;
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(firstName, lastName, phone, email, birthday);
    // }

    public boolean match(String term) {
        if (term.isBlank()) return false;

        if (this.getFirstName() != null && this.getFirstName().contains(term)) return true;
        if (this.getLastName() != null &&  this.getLastName().contains(term)) return true;
        if (this.getPhone() != null && this.getPhone().contains(term)) return true;
        if (this.getEmail() != null && this.getEmail().contains(term)) return true;
        if (this.getBirthday() != null && this.getBirthday().toString().contains(term)) return true;

        return false;
    }
}
