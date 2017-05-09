package com.demo.linksharing

abstract class Resource {

    String desctiption
    Date dateCreated
    Date lastUpdated

    static constraints = {
        desctiption(nullable: false, blank: false)
        createdBy(nullable: false)
        topic(nullable: false)
    }

    static belongsTo = [createdBy: User, topic: Topic]
    static hasMany = [ratings:ResourceRating,readingItem:ReadingItem]
}
