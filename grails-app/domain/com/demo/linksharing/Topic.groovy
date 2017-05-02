package com.demo.linksharing

import com.demo.linksharing.util.Seriousness
import com.demo.linksharing.util.Visibility

class Topic {

    static constraints = {
        topicName(nullable: false, unique: 'createdBy', blank: false)
        createdBy(nullable: false)
        visibility(nullable: false)
    }

    String topicName
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static belongsTo = [createdBy: User]
    static hasMany = [subscription: Subscription, resources: Resource]

    def afterInsert() {
        Topic.withNewSession {
            Subscription subscription = new Subscription(user: this.createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)
            if (!subscription.save(flush: true, failOnError: true)) {
                log.error "Subscription: ${subscription} was not saved to the database"
            } else {
                log.info " Subscription ${subscription} saved successfully"
            }
        }
    }
}