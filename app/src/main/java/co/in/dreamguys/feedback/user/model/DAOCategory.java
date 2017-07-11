package co.in.dreamguys.feedback.user.model;

/**
 * Created by Prasad on 7/11/2017.
 */

public class DAOCategory {

    private String categoryName, categoryid, intColorValue, intPercentageValue, answerId, answerName, totalCount;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getIntColorValue() {
        return intColorValue;
    }

    public void setIntColorValue(String intColorValue) {
        this.intColorValue = intColorValue;
    }

    public String getIntPercentageValue() {
        return intPercentageValue;
    }

    public void setIntPercentageValue(String intPercentageValue) {
        this.intPercentageValue = intPercentageValue;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
}
