package com.demo.linksharing


class LoginController {

    def index() {
        session["user"] = User.get(1)
        if (session.user != null) {
            session.message = "Congratulation $session.user"
            forward(controller: "user", action: "index")
        } else {
            render "Failure"
        }
    }

    def loginHandler(String username , String password) {
        User user = User.findByUsernameAndPassword(username,password)
        if(user!=null){
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
    def registration(){


    }

    def logout() {
        session.invalidate()
        forward(action:"index")

    }


}
