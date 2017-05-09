package com.demo.linksharing

import com.demo.linksharing.util.Visibility
import grails.transaction.Transactional
import org.hibernate.Session

@Transactional
class TopicService {

    def serviceMethod() {

    }

    def deleteTopic(Long id, User user) {
        Topic topic = Topic?.load(id)
        log.info("${topic}")
        log.info("${user}")
        if (topic) {
            if ((topic.createdBy.id == user.id)) {
                topic.delete(flush: true, failOnError: true)
                return "Topic Deleted"
            } else {
                return "Topic can not be deleted by ${user}"
            }
        } else {
            return "Topic not in database"

        }
        return "user is   ${user.id}  & topic created by is ${topic.createdBy.id}"

    }


    def showTopic(int id) {
        Topic topic = Topic.get(id)//Topic?.read(id)
        log.info(" topic recieved : $topic")
        if (topic != null) {
            return ("${topic.toString()}")
        } else {
            return "Topic not found"
        }
    }


    def addTopic(String topicName, String visibility, User user) {
//
        Topic topic = Topic.findOrCreateByCreatedByAndTopicName(user, topicName)
        topic.visibility = Visibility.getEnum(visibility)
        if (topic.save(flush: true)) {
            return "${topic} saved Successfully"
        }
    }
}
