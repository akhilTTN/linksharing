package com.demo.linksharing

class   ReadingItemController {

    def index() { }

    def changeIsRead(Long id, Boolean isRead){
//        log.info(">>>>>>>>>>>>>>>>>>   $id & $isRead >>>>>>>>>>")
        ReadingItem.changeIsRead(id,isRead,session.user.id)
        redirect(controller:'user',action:'index')
    }
}
