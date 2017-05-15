package com.demo.linksharing

import CO.SearchCO
import CO.TopicCO
import VO.InboxVO
import VO.PostsVO
import VO.TopicVO
import VO.UserDetailsVO
import com.demo.linksharing.util.Seriousness
import com.demo.linksharing.util.Visibility

class TopicController {

    def topicService


    def index() {}

    def show(CO.ResourceSearchCO resourceSearchCO, long id) {
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
        User user = session.user
        if (user) {
            render topicService.deleteTopic(id, user)
//        redirect(controller: 'login', action: 'index')
        }
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

    /*def shareTopic(String email, long id) {

        def msg;
        String inviter = session.user.userName
        String topicName = Topic.get(id).name
        mailService.sendMail {
            from "ishwarmanithapa@mgmail.com"
            to email
            cc "ishwarmani.thapa@tothenew.com"
            subject "invite from link sharing"
            text "Hi, You have been invite by ${inviter} to subscribe to ${topicName}"

        }
        flash.message = message(code: "invite.success")
        msg = flash.message
        redirect(controller: "user", action: "index", params: [message: msg])
    }*/

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
}
