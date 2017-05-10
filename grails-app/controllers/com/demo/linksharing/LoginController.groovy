package com.demo.linksharing


class LoginController {

    def index() {
//        session["user"] = User.get(1)
        if (session.user) {
            session.message = "Congratulation $session.user"
            forward(controller: "user", action: "index")
        } else {
            render view:'index'
        }
    }

    def loginHandler(String username , String password) {
        User user = User.findByUsernameAndPassword(username,password)
        if(user){
            if(user.active){
                session.user=user
                forward(action:"index")
            }
            else{
                render (flash.error = "Your account is not active. Contact the admin")
            }
        }
        else {
            render (flash.error = "user not found")
        }

    }

    //todo
    def register(){


    }

    def logout() {
        session.invalidate()
//        forward(action:"loginHandler")

    }


}
