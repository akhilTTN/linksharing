package com.demo.linksharing

class ResourceRating {

    Resource resource
    User user
    Integer score
    Date dateCreated
    Date lastUpdated

    static constraints = {
        resource(nullable: false, unique: 'user')
        user(nullable: false)
        score(unique: false, range: (1..5))
    }
    static belongsTo = [resource:Resource,user: User]
}