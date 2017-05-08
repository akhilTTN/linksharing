package com.demo.linksharing

import com.demo.linksharing.util.Seriousness

class SubscriptionController {

    def index() { }

    def save(Integer id) {
        Subscription subscription = new Subscription(user: User.get(session.user.id), topic: Topic.get(id))
        if (subscription.save(flush: true)) {
            flash.message = "Subscription saved successfully"
        } else {
            flash.error = subscription.errors.allErrors.collect { message(error: it) }.join(", ")
        }
        redirect(controller: 'user', action: 'index')
    }

    def update(Integer id, String serious) {
        Subscription subscription = Subscription.findByUserAndTopic(session.user, Topic.get(id))
        if (subscription && serious) {
            if (Seriousness.getEnum(serious) != null) {
                subscription.seriousness = Seriousness.getEnum(serious)
                if (subscription.save(flush: true, failOnError: true)) {
                    render "Subscription saved successfully"
                } else {
                    render "Error saving subscription"
                }
            } else {
                render "Proper Seriousness string not provided"
            }

        } else {
            render "Subscription not found Or seriousness not found"
        }
    }


    def delete(Integer id) {
        Subscription subscription = Subscription.load(id)
        if (subscription.topic.createdBy.id != session.user.id) {
            if (subscription) {
                subscription.delete(flush: true)
                flash.message = "Subscription deleted"
            } else {
                flash.error = "Error: Subscription not found"
            }
        } else {
            flash.error = "Creator can not delete subscription"
        }

        redirect(controller: 'user', action: 'index')
    }
}