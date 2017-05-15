package com.demo.linksharing


class SubscriptionInterceptor {

    SubscriptionInterceptor() {
//        match(action: 'save')
    }

    boolean before() {
        /*User user = session.user
        Topic topic = Topic.get(params.id)
        def isSubscribed = Subscription.findByTopicAndUser(topic, user)
        if (isSubscribed) {
            log.error("User ${user} is already subscribed to the ${topic}.")
            render("User ${user} is already subscribed to the ${topic}.")
            return false
        } else {
            return true
        }*/
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
