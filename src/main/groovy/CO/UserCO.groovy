package CO

import grails.validation.Validateable

/**
 * Created by akhil on 11/5/17.
 */
class UserCO implements Validateable{
    String firstName;
    String lastName;
    String email;
    String username
    String password;
//    boolean active;
    byte[] photo;
//    boolean admin
    String confirmPassword

    static constraints = {

        email(unique: true, blank: false, nullable: false, email: true)
        username(unique: true)
        password(blank: false, nullable: false, size: 5..15)
        firstName(blank: false, nullable: false)
        lastName(blank: false, nullable: false)
        photo(nullable: true)
    }

    @Override
    public String toString() {
        return "UserCO{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}

