package com.demo.linksharing

class RenderImageTagLib {

    static defaultEncodeAs = [taglib: 'text']
    static namespace = "a"

    def profileImage = { attrs, body ->
        User user = User.get(attrs.id)
        out << "<img alt='${user.name}' class='media-object recent-media-object-custom' height='100'  width='100'"+
                "src='${createLink(controller: 'user', action: 'image', params: [userId: attrs.id])}'>"
    }
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
}
