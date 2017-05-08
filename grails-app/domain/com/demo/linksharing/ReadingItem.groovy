package com.demo.linksharing

class ReadingItem {

/*    User user
    Resource resource*/
    Boolean isRead


    static constraints = {
        user nullable: false
        resource nullable: false, unique: 'user'
        isRead nullable: false
    }

    static belongsTo = [resource: Resource, user: User]

}
