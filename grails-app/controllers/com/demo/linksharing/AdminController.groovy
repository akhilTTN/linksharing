package linksharing

import com.demo.linksharing.Resource
import com.demo.linksharing.Topic
import com.demo.linksharing.User

class AdminController {

    def index() {
        List<User> userList = User.findAll()
        render view: "dashboard", model: ["users": userList]
    }


    def toggleActive(long id) {
        log.info("++++++++++++++++++++++++++++++++++++++++${id}")
        User user = User.get(id)
        log.info("----------------------------------------${user}")
        user.active = !user.active
        user.save(flush: true, failOnError: true, validate: false)
    }

    def showAllTopic(){
        List<Topic> topicList = Topic.findAll()
        render view:"topicList", model: ["topics":topicList]
    }


    def showAllPosts(){
        List<Resource> resourceList = Resource.findAll()
//        render "${resourceList}"
        render view:"postList", model: ["posts":resourceList]
    }

}
