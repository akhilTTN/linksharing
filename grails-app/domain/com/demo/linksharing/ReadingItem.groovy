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


    static def changeIsRead(Long id, Boolean isRead,long user_Id) {
        int flag = ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where resource_id =:id and user_id=:user_Id",
                [isRead: isRead, id: id,user_Id:user_Id])
        if (flag == 0){
            return "error"
        }
        else{
            return "successfully updated"
        }
    }

    @Override
    public String toString() {
        return "ReadingItem{" +
                "id=" + id +
                ", version=" + version +
                ", resource=" + resource +
                ", user=" + user +
                ", isRead=" + isRead +
                '}';
    }


}
