package com.demo.linksharing

import com.demo.linksharing.util.Seriousness

class Subscription {
    Seriousness seriousness = Seriousness.SERIOUS
    Date dateCreated
    Date lastUpdated
    static constraints = {
        user nullable: false, unique: 'topic'
        topic nullable: false, unique: 'user'
        seriousness nullable: false
    }
    static belongsTo = [user: User, topic: Topic]

    static mapping = {
        user lazy: false
        topic lazy: false
    }
}
