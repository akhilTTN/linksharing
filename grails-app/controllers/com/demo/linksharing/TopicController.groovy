package com.demo.linksharing

import com.demo.linksharing.util.Visibility

class TopicController {

    def topicService

    def index() {}

    def show(CO.ResourceSearchCO resourceSearchCO, int id) {
        render topicService.showTopic(id)
    }


    def save(String topicName, String visibility) {
        User user = session.user
        if (user) {
            render topicService.addTopic(topicName, visibility, user)
        } else
            render "Error saving topic Not saved"
    }


    def topicDelete(Long id) {
        User user = session.user
        if (user) {
            render topicService.deleteTopic(id, user)
//        redirect(controller: 'login', action: 'index')
        }
    }

    def search(){

    }
}
