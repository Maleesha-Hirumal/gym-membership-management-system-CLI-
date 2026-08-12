public class Member {
    private int memberID;
    private String name;
    private String email;
    private String phone;
    private int planID;
    private String planName;

    public Member(int memberID, String name, String email, String phone, int planID, String planName) {
        this.memberID = memberID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.planID = planID;
        this.planName = planName;
    }

    public int getMemberID() { return memberID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public int getPlanID() { return planID; }
    public String getPlanName() { return planName; }

    @Override
    public String toString() {
        return "ID: " + memberID + " | Name: " + name + " | Email: " + email
                + " | Phone: " + phone + " | Plan: " + planName;
    }
}
