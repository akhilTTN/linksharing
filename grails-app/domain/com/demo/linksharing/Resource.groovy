package com.demo.linksharing

abstract class Resource {

    User createdBy
    String desctiption
    Topic topic
    Date dateCreated
    Date lastUpdated

    static constraints = {
        desctiption(nullable: false, blank: false)
        createdBy(nullable: false)
        topic(nullable: false)
    }

    static belongsTo = [user: User, topic: Topic]
    static hasMany = [ratings:ResourceRating,readingItem:ReadingItem]
}
