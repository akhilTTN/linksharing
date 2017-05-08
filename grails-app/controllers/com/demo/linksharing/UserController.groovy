package com.demo.linksharing

import com.demo.linksharing.util.Visibility

class UserController {

    def index() {
//        render "user  dashboard"
        render "${session.user}"
    }

    def show(int id){
        Topic topic = Topic.get(id)
        if(topic){
            if(topic.visibility == Visibility.PUBLIC){
                render "success "
            }
            else if(topic.visibility == Visibility.PRIVATE){
                Subscription subscription =Subscription.findByTopic(topic)
                if(subscription){
                    render "<br/> <br/> success"
                }
                else{
                    flash.error = "Topic is private in the database and you are not subscribed to it"
                    redirect(controller:'login',action:'index')
                }
            }

        }
        else{
            flash.error = "Topic is not in the database"
            redirect(controller:'login',action:'index')
        }

    }


}
