package co

/**
 * Created by akhil on 9/5/17.
 */
class SearchCO {
    String q
    int max
    int offset
    String order
    String sort


    @Override
     String toString() {
        return "SearchCO{" +
                "q='" + q + '\'' +
                ", max=" + max +
                ", offset=" + offset +
                ", order='" + order + '\'' +
                ", sort='" + sort + '\'' +
                '}'
    }
}
