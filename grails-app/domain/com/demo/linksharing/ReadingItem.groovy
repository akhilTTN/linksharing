package com.demo.linksharing

class ReadingItem {

/*    User user
    Resource resource*/
    Boolean isRead


    static constraints = {
        user nullable: false
        resource nullable: false, unique: 'user'
        isRead nullable: false
    }

    static belongsTo = [resource: Resource, user: User]

    //todo GORM2 Q6
/*
Create readingItem/changeIsRead action which takes Long id and Boolean isRead
- User executeUpdate to change the isRead of readingItem with given id
- If value returned by executeUpdate is 0 then render error else render success
*/
//working fine
    static def changeIsRead(Long id, Boolean isRead) {
        int flag = ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id =:id", [isRead: isRead, id: id])
        if (flag == 0)
            return "error"
        else
            return "successfully updated"
    }

}
