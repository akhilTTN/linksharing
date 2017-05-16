package vo

/**
 * Created by akhil on 10/5/17.
 */
class RatingInfoVO {
    int totalScore
    int averageScore
    int totalVotes


    @Override
    public String toString() {
        return "RatingInfoVO{" +
                "totalScore=" + totalScore +
                ", averageScore=" + averageScore +
                ", totalVotes=" + totalVotes +
                '}';
    }
}
