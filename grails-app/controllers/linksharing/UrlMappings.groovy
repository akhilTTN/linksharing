package linksharing

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        "500"(view:'/error')
        "404"(view:'/notFound')
        "/"(controller:"login",action: "index")  //neeche wala override karega

    }
}
