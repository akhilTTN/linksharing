package com.demo.linksharing

class   ReadingItemController {

    def index() { }

    def changeIsRead(Long id, Boolean isRead){
        log.info(">>>>>>>>>>>>>>>>>>   $id & $isRead >>>>>>>>>>")
        render "${ReadingItem.changeIsRead(id,isRead,session.user.id)}"
    }
}
