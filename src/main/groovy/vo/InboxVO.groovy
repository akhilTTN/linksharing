package vo
/**
 * Created by akhil on 12/5/17.
 */
class InboxVO {
    String username
    String fullname
    String description
    String topicName


    @Override
    public String toString() {
        return "InboxVO{" +
                "username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", description='" + description + '\'' +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}
