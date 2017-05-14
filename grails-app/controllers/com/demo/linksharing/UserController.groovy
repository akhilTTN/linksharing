package com.demo.linksharing

import CO.SearchCO
import CO.UpdatePasswordCO
import CO.UserCO
import VO.InboxVO
import VO.PostsVO
import VO.SubscriptionVO
import VO.TopicVO
import VO.UserDetailsVO
import com.demo.linksharing.util.Visibility

class UserController {

    def index() {
        User user = session.user
        UserDetailsVO userDetailsVO = new UserDetailsVO()
        userDetailsVO.userFullName = user.getName()
        userDetailsVO.userName = user.username
        userDetailsVO.subscriptionCount = Subscription.countByUser(user)
        userDetailsVO.topicCount = Topic.countByCreatedBy(user)
//      profilePageVO.photo = session.user.photo
        userDetailsVO.userId = user.id
        List<TopicVO> subscriptionList = User.getSubscribedTopic(user)
        List<PostsVO> messages = user.getUnReadResources()
        log.info("------------------------------------------ $messages")
        render view: 'dashboard', model: [users       : userDetailsVO, subscriptionList: subscriptionList,
                                          resourceList: messages, message: params.message]
    }


    def show(int id) {
        Topic topic = Topic.get(id)
        if (topic) {
            if (topic.visibility == Visibility.PUBLIC) {
                render "success "
            } else if (topic.visibility == Visibility.PRIVATE) {
                Subscription subscription = Subscription.findByTopic(topic)
                if (subscription) {
                    render "<br/> <br/> success"
                } else {
                    flash.error = "Topic is private in the database and you are not subscribed to it"
                    redirect(controller: 'login', action: 'index')
                }
            }

        } else {
            flash.error = "Topic is not in the database"
            redirect(controller: 'login', action: 'index')
        }

    }


    def image(Long userId) {
        User user = User.load(userId)
        byte[] photo
        if (!user?.photo) {
            photo = assetResourceLocator.findAssetForURI('user.png').byteArray
        } else {
            photo = user.photo
        }
        OutputStream out = response.getOutputStream()
        out.write(photo)
        out.flush()
        out.close()
    }


    def unread(SearchCO searchCO) {
        log.info("${searchCO.q}")
        List list = session.user.getUnReadResources()
        println(list)
        render(list)
    }

    def profile(long id) {

        User user = User.get(id)

        UserDetailsVO userDetailsVO = new UserDetailsVO()
        userDetailsVO.userFullName = user.getName()
        userDetailsVO.userName = user.username
        userDetailsVO.subscriptionCount = Subscription.countByUser(user)
        userDetailsVO.topicCount = Topic.countByCreatedBy(user)
        userDetailsVO.userId = user.id

        List<TopicVO> subscriptionList = User.getSubscribedTopic(user)
        List<TopicVO> topicsCreated = User.allCreatedTopics(user)
        List<PostsVO> allPosts = User.allCreatedPost(user)
//        users: userDetailsVO,
        render view: "profile", model: ['subscriptionList': subscriptionList,
                                        topicsCreated     : topicsCreated, allPosts: allPosts]
    }


    def edit() {
        List<TopicVO> topicsCreated = User.allCreatedTopics(session.user)
        render view: "edit", model: [topicsCreated: topicsCreated]
    }

    def updatePassword(UpdatePasswordCO co) {
        def msg = flash.message ?: ""
        log.info("$co")
        User user = User.get(session.user.id)
        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa $user")
        if (co.password.equals(co.confirmPassword)) {
            if (user.password.equals(co.oldPassword)) {
                user.password = co.password
                user.confirmPassword = co.confirmPassword
                if (user.save(failOnError: true, flush: true)) {
                    flash.message = message(code: "password.changed")
                    msg = flash.message
                    session.user = user
                } else {
                    flash.error = "Error in updating password"
//                    msg = flash.message
                }
//            }

            } else {
                flash.error = "Old Password do not match!"
//                msg = flash.message
            }
        } else {
            flash.error = " Passwords do not match!"
//            msg = flash.message
        }


        redirect(controller: "user", action: "editProfile", params: [message: msg])
    }


    def updateProfile(UserCO co) {
        def msg = flash.message ?: ""
        User user = User.findByUsername(session.user.username)
        if (user) {
            if (co.firstName)
                user.firstName = co.firstName
            if (co.lastName)
                user.lastName = co.lastName
            if (co.username)
                user.username = co.username
            if (user)
                user.photo = co.photo
            if (user.save(failOnError: true, flush: true)) {
                flash.message = message(code: "profile.updated")
                msg = flash.message
                session.user = user
            } else {
                flash.error = "Error updating profile"
//                msg = flash.message
            }
        } else {
            flash.error = "User Not Found"
//            msg = flash.message
        }
        redirect(controller: "user", action: "editProfile", params: [message: msg])
    }


    def editProfile() {
        render view: "edit", model: [message: params.message]
    }

}
