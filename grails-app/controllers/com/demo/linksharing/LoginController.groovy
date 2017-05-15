package com.demo.linksharing

import CO.UserCO


class LoginController {

    def userService

    def index() {
//        session["user"] = User.get(1)
        /*if (session.user) {
            session.message = "Congratulation $session.user"
            forward(controller: "user", action: "index")
        } else {
            render view: 'index', model: [topPost: Resource.topPost()]
        }*/
        redirect(controller: "user", action: "index")

    }

    def loginHandler(String username, String password) {
        User user = User.findByUsernameAndPassword(username, password)
        if (user) {
            if (user.active) {
                session.user = user
                flash.message = message(code: "successful.login", args: [user.username])
                redirect(action: "index", params: [message: flash.message])
            } else {
                flash.message = message(code: "account.not.active", args: [user.username]);
                redirect controller: 'login', action: 'index'
            }
        } else {
//            render (flash.error = "user not found")
            flash.error = "User not found"
            render(view: "/user/regIndex")
        }

    }

    /*def abc(){
        render "${Resource.topPost()}"
    }*/

    //todo
    def register(UserCO userCO) {
        log.info("${userCO}")
        String photoPath = userService.upload(userCO.username,userCO.photo, request.getSession().getServletContext().getRealPath("/"))
        User user = new User(firstName: userCO.firstName, lastName: userCO.lastName,photoPath: photoPath, email: userCO.email,
                username: userCO.username, password: userCO.password, confirmPassword: userCO.confirmPassword)
        user.save(failOnError: true, flush: true)
        if (user.hasErrors()) {
            flash.error = "sorry you can't be registered."
            render "${flash.error}"
        } else {
            redirect action: 'loginHandler', params:[username:"${userCO.username}",password:"${userCO.password}"]
        }

    }


    def logout() {
        session.invalidate()
        forward(action: "index")

    }


}
