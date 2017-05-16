package com.demo.linksharing

import co.SearchCO
import co.TopicCO
import vo.InboxVO
import vo.PostsVO
import vo.TopicVO
import vo.UserDetailsVO
import com.demo.linksharing.util.Seriousness
import com.demo.linksharing.util.Visibility

class TopicController {

    def topicService
    def mailService


    def index() {}

    def show(co.ResourceSearchCO resourceSearchCO, long id) {
        Topic topic = Topic.read(id)
        TopicVO topicVO = new TopicVO(id: topic.id, topicName: topic.topicName, visibility: topic.visibility,
                createdBy: topic.createdBy, count: topic.resources.size(),
                subsCount: topic.subscription.size())

        List<UserDetailsVO> subscribedUsers = Topic.getSubscribedUsers(topic)
        List<PostsVO> resourcesList = Resource.getAllResources(topic)
        log.info("$resourcesList is a topic read")
        if (topic == null) {
            log.info("topic is null.")
            flash.error = "topic doesn't exist."
            redirect(controller: "login", action: "index")
        } else if (topic.visibility == Visibility.PUBLIC) {
            render view: 'topicShow', model: [topics: topicVO, posts: resourcesList, subscribedUsers: subscribedUsers]
        } else {
            if (topic.visibility == Visibility.PRIVATE) {
                if (Subscription.findByUserAndTopic(session.user, topic)) {
                    render view: 'topicShow', model: [topics: topicVO, posts: resourcesList, subscribedUsers: subscribedUsers]
                } else {
                    flash.error = "you are not subscribed for this topic."
                    redirect(controller: "login", action: "index")
                }
            }
        }
    }


    def save(TopicCO topicCO) {
        log.info("${topicCO.toString()}")
//        render topicCO.toString()
        topicService.createTopic(topicCO, session.user)
        redirect(controller: 'user', action: 'index')
    }


    def topicDelete(Long id) {
        /*User user = session.user
        if (user) {
            topicService.deleteTopic(id, user)
        redirect(controller: 'user', action: 'index')
        }*/

        Topic topic = Topic?.load(id)
        if(topic){
            if(topic.createdBy.username == session.user.username){
                topic.delete(flush:true)
                flash.message = "Topic deleted"
            }
            else {
                flash.error="Can not deletet topic"
            }
        }
        redirect(controller: 'user',action: 'index')
    }


    def showTrending() {
        List topicVO = Topic.getTrendingTopics()
        log.info("$topicVO")
        render "$topicVO"
    }


    def populate() {
        User.allCreatedTopics(session.user)
    }

    def search(SearchCO searchCO) {
        List<InboxVO> searchResult = Topic.getSearched(searchCO)
        log.info("$searchResult")
        render(view: "search", model: ["totalResult": searchResult.size(), "searchResult": searchResult, 'topics': new TopicVO(name: searchCO.q)])
    }

    def shareTopic(String email, long id) {

        def msg;
        String inviter = session.user.username
        String topicName = Topic.get(id).topicName
        mailService.sendMail {
            from "akhil@tothenew.com"
            to email
            cc "akhilsr20@gmail.com"
            subject "invite from link sharing"
            text "Hi, You have been invite by ${inviter} to subscribe to ${topicName}"

        }
        flash.message = message(code: "invite.success")
        msg = flash.message
        redirect(controller: "user", action: "index", params: [message: msg])
    }

    def toggleSubscription(long id) {
        log.info("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk ${id}")
        Topic topic = Topic.get(id)
        Subscription subscription = Subscription.findByUserAndTopic(session.user, topic)
        log.info("${topic} ^^^^^^^^^^^^^^^^^^^ ${subscription}")
        if(subscription){
            boolean b=subscription.delete(flush:true)
            return b
        }
        else {
            log.info("${topic}&&&&&&&&&&&&&&&&&&&&&&&&")
            boolean b=topic.addToSubscription(new Subscription(user: session.user, topic: topic, seriousness: Seriousness.VERY_SERIOUS).save(flush:true))
            log.info("${topic}&&&&&&&&&&&&&&&&&&&&&&&& ${b}")
            return b
        }

    }

    def editTopicName(String newTopicName, long topicID){
        Topic topic = Topic.get(topicID)
        topic.topicName = newTopicName
        topic.save(flush:true)
//        render "${topic.topicName}"
        redirect(controller: 'user', action: 'index')
    }




    /*def changeSeriousness(long id){
        Topic topic = Topic.get(id)
        User user = session.user
        Subscription subscription = Subscription.findByTopicAndUser(topic,user)
        subscription.seriousness =

    }*/
}
