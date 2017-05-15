package com.demo.linksharing

import com.demo.linksharing.util.Seriousness

class SubscriptionController {

    def subscriptionService

    def index() {}

    def save(Integer id) {
        User user = session.user
        subscriptionService.save(id, user)
    }

    def update(long id, String seriousness) {
//        println "*****************************************************************"
        Subscription subscription = Subscription.get(id)
        def seriousness1 = Seriousness.getEnum(seriousness)
        log.info("+++++++++++++++++ $seriousness1 $id")
        if (subscription != null && seriousness1 instanceof Seriousness) {
            subscription.seriousness = seriousness1
            subscription.save(flush: true, failOnError: true)
            if (subscription.hasErrors()) {
                log.info("${subscription.errors.allErrors}")
//                render "${subscription.errors.allErrors}"
                return  "Error"
            } else {
                log.info("$subscription is updated")
//                render "$subscription is updated"
                return  "Error"
            }
        } else {
            return  "subscription can't be updated"
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

    def allSubscribedUsers() {
        println("hello from subscribed users")
        Topic topic = Topic.get(1)
        def list = topic.getSubscribedUsers(topic)
        render list
    }
}
