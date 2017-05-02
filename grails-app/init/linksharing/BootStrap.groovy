package linksharing

import com.demo.linksharing.User

class BootStrap {

    def init = { servletContext ->
        createUser()
        def destroy = {
        }
    }

    def createUser() {
        if (User.count == 0) {
            log.info "=================Creating Normal User======================="
            User normalUser = new User(firstName: "test", lastName: "test", username: "test123", photo: null, admin: false, active: true, email: "test@tothenew.com", password: "tests")
            normalUser.save(failOnError: true, flush: true)
            log.info "=================Creating Normal User======================="
            if (normalUser.hasErrors()) {
                normalUser.errors.allErrors.each {
                    log.error it
                }
            }
        } else {
            log.info("User already present in the table")
        }
        log.info "=================Creating ADMIN======================="
        User admin = new User(firstName: "Akhil", lastName: "Rawat", username: "akhil123", photo: null, admin: true, active: true, email: "akhil@tothenew.com", password: "akhil123")
        admin.save(failOnError: true, flush: true)
        log.info "=================Creating ADMIN======================="
        if (admin.hasErrors()) {
            admin.errors.allErrors.each {
                log.error it
            }
        }
    }

}