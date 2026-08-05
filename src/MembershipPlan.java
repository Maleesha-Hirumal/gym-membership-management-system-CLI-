public class MembershipPlan {
    private int planID;
    private String planName;
    private int durationMonths;
    private double fee;

    public MembershipPlan(int planID, String planName, int durationMonths, double fee) {
        this.planID = planID;
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.fee = fee;
    }

    public int getPlanID() { return planID; }
    public String getPlanName() { return planName; }
    public int getDurationMonths() { return durationMonths; }
    public double getFee() { return fee; }

    @Override
    public String toString() {
        return planName + " (" + durationMonths + " months, Rs. " + fee + ")";
    }
}
