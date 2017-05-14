package VO

/**
 * Created by akhil on 14/5/17.
 */
class PostDetailVO {
    String username
    String fullName
    String topicName
    String description
    int ratings
    Date updated
    long resourceID


    @Override
    public String toString() {
        return "PostDetailVO{" +
                "userName='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", topicName='" + topicName + '\'' +
                ", description='" + description + '\'' +
                ", ratings=" + ratings +
                ", updated=" + updated +
                ", resourceID=" + resourceID +
                '}';
    }
}
