package cs211.project.models.eventHub;

import java.util.ArrayList;

public class MembershipList {
    private ArrayList<Membership> membershipsList;

    public MembershipList() {
        membershipsList = new ArrayList<>();
    }
    public void addMember(Membership member) {
        membershipsList.add(member);
    }
    public void addMember(String name, String eventID) {
        Membership member = new Membership(name, eventID, "AUDIENCE");
        membershipsList.add(member);
    }
    public ArrayList<Membership> getMembershipsList() {
        return membershipsList;
    }
}