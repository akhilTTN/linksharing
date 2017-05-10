package com.demo.linksharing

import CO.SearchCO
import org.hibernate.sql.JoinType

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


    /*
//todo GORM2 Q6) Add Inbox feature on user/index when user is loggedin
- Create method getUnReadResources in user domain which takes SearchCO argument and returns unreaditems of user from ReadingItem domain
- The search should also work using user/index page, q parameter of SearchCO. If searchco.q is found then getUnReadResources method
will search the items based on ilike of resource.description.
- The pagination parameter should also be used in getUnReadResources criteria query. Create readingItem/changeIsRead action which
takes Long id and Boolean isRead
- User executeUpdate to change the isRead of readingItem with given id
- If value returned by executeUpdate is 0 then render error else render success
*/

    static def getUnReadResources(SearchCO searchCO) {
        List result = ReadingItem.createCriteria().list {
// resultTransform Transformers = Transformers.aliasToBean(ResourceDTO)
            if (searchCO && searchCO.q) {
                createAlias("resource", "r", JoinType.LEFT_OUTER_JOIN)
                ilike("r.description", "%${searchCO.q}%")
// eq('resource',Resource.findByDescriptionIlike(searchCO.q))
                projections {
                    property('id')
                    property('r.description')
                }
            }
            eq('isRead', false)
            maxResults 5
// eq('user',this)
        }
        println(result)
        result
    }
}