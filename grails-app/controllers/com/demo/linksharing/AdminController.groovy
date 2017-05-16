package linksharing

import com.demo.linksharing.Resource
import com.demo.linksharing.Topic
import com.demo.linksharing.User

class AdminController {

    def index() {

        int offset, max
        if (params.offset)
            offset = params.int('offset')
        if (params.max)
            max = params.int('max')

//        List<User> userList = User.findAll()
        if (!offset)
            offset = 0
        if (!max)
            max = 5
        List<User> userList = User.createCriteria().list(offset: offset, max: 5) { }
        render view: "dashboard", model: ["users": userList]
    }


    def toggleActive(long id) {
        log.info("++++++++++++++++++++++++++++++++++++++++${id}")
        User user = User.get(id)
        log.info("----------------------------------------${user}")
        user.active = !user.active
        user.save(flush: true, failOnError: true, validate: false)
    }

    def showAllTopic() {
        int offset, max
        if (params.offset)
            offset = params.int('offset')
        if (params.max)
            max = params.int('max')

        if (!offset)
            offset = 0
        if (!max)
            max = 5

        List<Topic> topicList = Topic.createCriteria().list(offset:offset,max:max) {}
        render view: "topicList", model: ["topics": topicList]
    }


    def showAllPosts() {
        int offset, max
        if (params.offset)
            offset = params.int('offset')
        if (params.max)
            max = params.int('max')

        if (!offset)
            offset = 0
        if (!max)
            max = 10

        List<Resource> resourceList = Resource.createCriteria().list (offset:offset,max:max){}
//        render "${resourceList}"
        render view: "postList", model: ["posts": resourceList]
    }

}
