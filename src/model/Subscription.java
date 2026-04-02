package model;

public class Subscription {
    private int subscriptionId;
    private String subscriptionName;
    private String benefits;
    private int benefitsUsed;
    private int remainingBenefits;

    public Subscription(int subscriptionId, String subscriptionName, String benefits, int benefitsUsed, int remainingBenefits) {
        this.subscriptionId = subscriptionId;
        this.subscriptionName = subscriptionName;
        this.benefits = benefits;
        this.benefitsUsed = benefitsUsed;
        this.remainingBenefits = remainingBenefits;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public int getBenefitsUsed() {
        return benefitsUsed;
    }

    public void setBenefitsUsed(int benefitsUsed) {
        this.benefitsUsed = benefitsUsed;
    }

    public int getRemainingBenefits() {
        return remainingBenefits;
    }

    public void setRemainingBenefits(int remainingBenefits) {
        this.remainingBenefits = remainingBenefits;

    }
}
