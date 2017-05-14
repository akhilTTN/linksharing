package com.demo.linksharing

import com.demo.linksharing.util.Seriousness
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

    def subscribeCreator(Topic topic, Seriousness seriousness) {
        def notSubscribed = Subscription.findByTopicAndUser(topic, topic.createdBy);
        if (notSubscribed == null) {
            Subscription subscription = new Subscription(topic: topic, user: topic.createdBy, seriousness: seriousness)

//            topic.addToSubscriptions(subscription)
            subscription.save()
//            throw new IOException()
            if (subscription.hasErrors()) {
                log.info("${subscription.errors.allErrors}")
                return "${subscription.errors.allErrors}"
            } else {
                log.info("new subscription is made for $topic.createdBy in topic ${topic.topicName}")
                return "new subscription is made for $topic.createdBy in topic ${topic.topicName}"
            }
        } else {
            return "subscription already present"
        }
    }
}
