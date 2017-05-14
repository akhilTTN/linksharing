package linksharing

import com.sun.jndi.cosnaming.ExceptionMapper

//import org.hibernate.resource.transaction.backend.jta.internal.synchronization.ExceptionMapper

class ErrorController {


    def index() {

        def exception = request.exception.cause
        def message = ExceptionMapper.mapException(exception)
        def status = message.status

        response.status = status
        render(view: "/error", model: [status: status, exception: exception])
    }
}
