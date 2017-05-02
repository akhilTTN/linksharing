package com.demo.linksharing

import com.demo.linksharing.util.Visibility

class Topic {

    static constraints = {
        topicName(nullable: false, unique: 'createdBy',blank: false)
        createdBy(nullable: false)
        visibility(nullable: false)
    }

    String topicName
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static belongsTo = [createdBy : User ]
    static hasMany = [subscription:Subscription, resources:Resource]

}
