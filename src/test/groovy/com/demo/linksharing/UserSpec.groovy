package com.demo.linksharing

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestMixin(DomainClassUnitTestMixin)
@TestFor(User)
class UserSpec extends Specification {

    def "canary"() {
        expect: true
    }

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("#sno")
    void "testingEmail"() {
        setup:
        User user = new User(email: email)

//        expect:
//        result == user.validate([email])
        when:
        Boolean valid = user.validate(["email"])

        then:
        valid == result

        where:
        sno | email                | result
        1   | "akhil@tothenew.com" | true
        2   | "akhil@tothenew"     | false
        3   | "akhiltothenew.com"  | false
        4   | "akhiltothenewcom"   | false
        5   | ""                   | false
        6   | null                 | false
        7   | "@tothenew"          | false
        8   | ".com"               | false
    }

    @Unroll
    def "TestForFirstName"() {
        setup:
        User user = new User(firstName: firstName)

        when:
        Boolean valid = user.validate(["firstName"])

        then:
        valid == result

        where:
        sno | firstName            | result
        1   | "akhil@tothenew.com" | true
        2   | "akhil@tothenew"     | true
        3   | "akhiltothenew.com"  | true
        4   | "akhiltothenewcom"   | true
        5   | ""                   | false
        6   | null                 | false
        7   | "@tothenew"          | true
        9   | ".com"               | true
        10  | "123"                | false
        11  | "0123akhil"          | false
        12  | "@@@@@@@@@"          | true
        13  | "akhil"              | true


    }


    def "TestForLastName"() {
        setup:
        User user = new User(lastName: lastname)

        when:
        Boolean valid = user.validate(["lastName"])

        then:
        valid == result

        where:
        sno | lastname             | result
        1   | "akhil@tothenew.com" | true
        2   | "akhil@tothenew"     | true
        3   | "akhiltothenew.com"  | true
        4   | "akhiltothenewcom"   | true
        5   | ""                   | false
        6   | null                 | false
        7   | "@tothenew"          | true
        9   | ".com"               | true
        10  | "123"                | false
        11  | "0123akhil"          | false
        12  | "@@@@@@@@@"          | true
        13  | "akhil"              | true


    }


    def "TestForUserName"() {

        setup:
        User user = new User(username: username);

        when:
        Boolean result = user.validate(["username"])

        then:
        result == value

        where:

        sno | username             | value
        1   | "akhil@tothenew.com" | true
        2   | "akhil@tothenew"     | true
        3   | "akhil123"           | true
        4   | "123akhil"           | true
        5   | "123123"             | true

    }


    def "TestingForUniqueUserName"() {

    }

    @Unroll
    void 'testingtheusersthataregettingcreatedintheuserdomain'() {
        setup:
        User user = new User(email: email, username: userName, password: password, firstName: fname, lastName: lname, photo: photo,
                admin: admin, active: active)

        expect:
        result == user.validate()

        where:
        sno | email                | userName | password   | fname   | lname   | photo | admin | active | result
        1   | "akhil@tothenew.com" | "akhil"  | "abcd1234" | "Akhil" | "Rawat" | null  | true  | true   | true

    }


    def "EmailAddressOfEmployeeShouldBeUnique"() {
        setup:
        String email = "test@tothenew.com"
        String password = 'password'
        User user = new User(firstName: "test", lastName: "test", username: "test123", photo: null, admin: null, active: null, email: email, password: password)

        when:
        user.save(flush: true)
        then:
        user.count() == 1

        when:
        User newUser = new User(firstName: "test", lastName: "test", username: "test1234", photo: null, admin: null, active: null, email: email, password: password)
        newUser.save(flush: true)

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('email') == 1
    }


    def "usernameOfTheUserShouldBeUnique"() {
        setup:
        String username = "akhil123"
        User user = new User(firstName: "test", lastName: "test", username: username, photo: null, admin: null, active: null, email: "abcd@mmm.com", password: "xcvbn")

        when:
        user.save(flush: true)
        then:
        user.count() == 1

        when:
        User newUser = new User(firstName: "test", lastName: "test", username: username, photo: null, admin: null, active: null, email: "aasad@fssdf.com", password: "cfghvhjbk")
        newUser.save(flush: true)

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('username') == 1
    }


    @Unroll
    def "TestingOfPasswordFiled"() {
        setup:
        User user = new User(firstName: firstname, lastName: "test", username: "abcd", photo: null, admin: null, active: null, email: "abcd@mmm.com", password: password)

        when:
        Boolean result = user.validate(firstName: firstname, lastName: "test", username: "abcd", photo: null, admin: null, active: null, email: "abcd@mmm.com", password: password)

        then:
        result == value

        where:
        sno | password      | firstname | value
        1   | "abcd12345"   | "akhil"   | true
        2   | "12345678910" | "akhil"   | true
        3   | ""            | "akhil"   | false
        4   | null          | "akhil"   | false
        5   | "1234"        | "akhil"   | false
        6   | "abcd"        | "akhil"   | false
        7   | "akhil"       | "akhil"   | false
        8   | "akhil123"    | "akhil"   | true

    }
}
