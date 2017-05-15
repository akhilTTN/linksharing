package linksharing

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

}
