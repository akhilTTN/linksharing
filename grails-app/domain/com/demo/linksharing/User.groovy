package com.demo.linksharing

class User {

    static constraints = {
        firstName(blank: false, nullable: false, validator: { val, obj ->
            val.matches(".*\\d+.*") ? ["com.ttn.linksharing.User.firstName.validator"] : true
        })
        lastName(blank: false, nullable: false, validator: { val, obj ->
            val.matches(".*\\d+.*") ? ["com.ttn.linksharing.User.firstName.validator"] : true
        })
        username(blank: false, nullable: false, unique: true)
        email(blank: false, nullable: false, unique: true, email: true)
        password(blank: false, nullable: false, minSize: 5, validator: { val, obj ->
            if (val.equalsIgnoreCase(obj.firstName)) {
                return 'com.ttn.linksharing.User.password.validator'
            } else
                return true
        })
        photo(nullable: true)
        admin(nullable: true)
        active(nullable: true)
        confirmPassword(blank: true, nullable: true, validator: { val, obj ->
            if (!val.equals(obj.password)) {
                return 'com.ttn.linksharing.User.confirmPassword.validator'
            }
        })
    }


    String firstName;
    String lastName;
    String email
    String username
    String password
    Byte[] photo
    Boolean admin
    Boolean active
    String confirmPassword
    Date dateCreated
    Date lastUpdated

    static transients = ['name', 'confirmPassword']

    String getName() {
        return (firstName + " " + lastName)
    }

    static hasMany = [topics: Topic, subscription: Subscription, resources: Resource, reads: ReadingItem]


    static mapping = {
        photo(sqlType: 'longBlob')
        sort id: 'desc'
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}