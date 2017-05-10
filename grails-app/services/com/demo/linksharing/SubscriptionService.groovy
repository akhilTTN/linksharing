package com.demo.linksharing

import grails.transaction.Transactional

@Transactional
class SubscriptionService {

    def serviceMethod() {

    }

    def save(long id, User user){
        Subscription subscription = new Subscription(user: user, topic: Topic.get(id))
        if (subscription.save(flush: true)) {
            return "Subscription saved successfully"
        } else {
            return (subscription.errors.allErrors.collect { it }.join(", "))
        }
    }
}
