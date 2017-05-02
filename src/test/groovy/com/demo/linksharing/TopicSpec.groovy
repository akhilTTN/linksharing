package com.demo.linksharing

import com.demo.linksharing.util.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestMixin(DomainClassUnitTestMixin)
@TestFor(Topic)
class TopicSpec extends Specification {

    void "testingTopicName"() {
        setup:
        Topic topic = new Topic(topicName: topicname);

        when:
        Boolean result = topic.validate(["topicName"])

        then:
        result == value

        where:
        sno | topicname | value
        1   | "akhil"   | true
        2   | "AAAAA"   | true
        3   | "123"     | true
        4   | "12aaa12" | true
        5   | "@@@@@@"  | true
        6   | ""        | false
        7   | null      | false

    }




    // if i save user here i get an error saying user is not a domain class or gorm is not set up properly. why??
    def "TopicCreatedByOneUserShouldBeUnique"() {
        setup:
        String topicName = "akhil"
        User user = new User(firstName: "test", lastName: "test", username: "abcd", photo: null, admin: null, active: null, email: "test@test.com", password: "abcde12345")
//        user.save()
        Topic topic = new Topic(topicName: topicName , createdBy: user,visibility: Visibility.PRIVATE)

        when:
        topic.save()

        then:
        topic.count() == 1

        when:
        Topic newTopic = new Topic(topicName: topicName , createdBy: user,visibility: Visibility.PRIVATE)
        newTopic.save()

        then:
        Topic.count() == 1
        newTopic.errors.allErrors.size() == 1
        newTopic.errors.getFieldErrorCount('topicName') == 1
    }

}
