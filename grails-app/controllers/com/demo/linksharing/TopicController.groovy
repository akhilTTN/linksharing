package com.demo.linksharing

import com.demo.linksharing.util.Visibility

class TopicController {

    def index() { }

    def show(int id){
        Topic topic = Topic.read(id)
        if(topic){
            render("${params.toString()}")
        }
        else{
            render "Topic not found"
        }
    }

    def delete(int id){
        Topic topic = Topic.load(id)
        if(topic)
            topic.delete()
        else
            render "topic not found"
    }


    def save(String topicName, String visibility){
        User user=session.user
        Topic topic = Topic.findOrCreateByCreatedByAndTopicName(user,topicName)
        topic.visibility = Visibility.getEnum(visibility)
        if (topic.save(flush: true)) {
            render flash.message = "${topic} saved Successfully"
        } else {
            render flash.error = "Error saving ${topic} Not saved"
        }

    }

    //todo
    def topicSave(){

    }

    def topicDelete(Long id) {

        Topic topic = Topic.load(id)
        User user = session.user

        if (topic) {
            if (user.admin || (topic.createdBy.id == user.id)) {
                topic.delete(flush: true)
                flash.message = "Topic Deleted"
            } else {
                flash.error = "Topic can not be deleted by ${session.user}"
            }
        } else {
            flash.error = "Topic not in database"

        }

        redirect(controller: 'login', action: 'index')
    }
}
