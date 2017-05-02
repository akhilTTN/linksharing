package com.demo.linksharing

import com.demo.linksharing.util.Seriousness
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestMixin(DomainClassUnitTestMixin)
@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }


    def "validatingSubscription"() {
        given:
        Subscription subscription = new Subscription(topic: topic, user: user, seriousness: seriousness)

        when:
        Boolean valid = subscription.validate()

        then:
        valid == result

        where:
        topic       | user       | seriousness         | result
        new Topic() | new User() | Seriousness.CASUAL  | true
        null        | new User() | Seriousness.SERIOUS | false
        new Topic() | null       | Seriousness.CASUAL  | false
        new Topic() | new User() | null                | false
    }



    def "validatingDuplicateSubscription"() {

        given:
        Topic topic = new Topic()
        User user = new User()
        Subscription subscriptionObj = new Subscription(topic: topic, user: user, seriousness: Seriousness.SERIOUS)

        when:
        subscriptionObj.save()

        then:
        Subscription.count() == 1

        when:
        subscriptionObj = new Subscription(topic: topic, user: user, seriousness: Seriousness.SERIOUS)
        subscriptionObj.save()

        then:
        Subscription.count() == 1
        subscriptionObj.errors.allErrors.size() == 2
        subscriptionObj.errors.getFieldErrorCount('user') == 1
        subscriptionObj.errors.getFieldErrorCount('topic') == 1
    }


}
