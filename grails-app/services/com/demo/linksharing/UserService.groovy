package com.demo.linksharing

import com.demo.linksharing.util.Visibility
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class UserService {




    def serviceMethod() {

    }

    def upload(String username, MultipartFile file, String rootDir) {
        if (file.empty) {
            flash.message = 'file cannot be empty'
            render("error")
            return
        }
        File fileDest = new File(rootDir, "/uploads/${username}_${file.originalFilename}")
        file.transferTo(fileDest)

        return "${rootDir}/uploads/${file.originalFilename}"

    }

    def show(Integer id) {
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

}
