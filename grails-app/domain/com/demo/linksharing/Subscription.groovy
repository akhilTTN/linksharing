package com.demo.linksharing

import com.demo.linksharing.util.Seriousness

class Subscription {


    User user
    Topic topic
    Seriousness seriousness
    Date dateCreated
    Date lastUpdated
    static constraints = {
        user nullable: false, unique: 'topic'
        topic nullable: false, unique: 'user'
        seriousness nullable: false
    }
    static belongsTo = [user:User , topic: Topic]
}