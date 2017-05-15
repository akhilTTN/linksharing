package VO

/**
 * Created by akhil on 14/5/17.
 */
class UserDetailsVO {
    String userName
    String userFullName
    int subscriptionCount
    int topicCount
    byte[] photo//todo
    long userId


    @Override
    public String toString() {
        return "UserDetailsVO{" +
                "userName='" + userName + '\'' +
                ", userFullName='" + userFullName + '\'' +
                ", subscriptionCount=" + subscriptionCount +
                ", topicCount=" + topicCount +
                ", photo=" + Arrays.toString(photo) +
                ", userId=" + userId +
                '}';
    }
}
