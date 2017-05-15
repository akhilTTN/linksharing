package linksharing

import com.demo.linksharing.User

class ImageTagLib {
    static defaultEncodeAs = [taglib:'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "pic"
    /*def imageFile ={ attrs ->
        def userInstance = User.get(attrs.id)
        byte[] image = userInstance.photo
        if (!userInstance.photo ){
            log.info "eoeoeoeoeoeoeo"
            flash.error = "error!!!!!!!!!"
            return
        }
        OutputStream outputStream = response.getOutputStream()
        outputStream << image
        outputStream.flush()
        outputStream.close()
    }*/

    def userImage = { attrs, body ->
        User user = User.get(attrs.id)
        out << "<img alt='${user.username}' height='100' width='100' class='media-object recent-media-object-custom' " +
                "src='${createLink(controller: 'user', action: 'image', params: [userId: attrs.id])}'>"
    }
}

