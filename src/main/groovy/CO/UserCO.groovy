package CO

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

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
    MultipartFile photo;
//    boolean admin
    String confirmPassword

    static constraints = {

        email(unique: true, blank: false, nullable: false, email: true)
        username(unique: true)
        password(blank: true, nullable: true, size: 5..15)
        firstName(blank: false, nullable: false)
        lastName(blank: false, nullable: false)
        photo(nullable: true)
        confirmPassword(nullable: true, blank: true)
    }

    @Override
    public String toString() {
        return "UserCO{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
//                ", photo=" + Arrays.toString(photo) +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}

