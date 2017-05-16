package com.demo.linksharing

import co.TopicCO
import com.demo.linksharing.util.Seriousness
import com.demo.linksharing.util.Visibility
import grails.transaction.Transactional

@Transactional
class TopicService {

    def subscriptionService

    def serviceMethod() {

    }

    def deleteTopic(Long id, User user) {
        /*Topic topic = Topic?.load(id)
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
*/
    }


    /*def showTopic(int id) {
        Topic topic = Topic.get(id)//Topic?.read(id)
        log.info(" topic recieved : $topic")
        if (topic != null) {
            return ("${topic.toString()}")
        } else {
            return "Topic not found"
        }
    }*/


    def createTopic(TopicCO topicCO, User user) {
//        return  "${topicCO.topicName}    &&&&&&&&&&&&&&&7 ${topicCO.visibility}"
        Topic topic = new Topic(topicName: topicCO.topicName,visibility: Visibility.getEnum(topicCO.visibility),createdBy: user)
        topic.save(flush:true,failOnError:true)
        // throw new IOException()
        subscriptionService.subscribeCreator(topic,Seriousness.SERIOUS)
    }
}
