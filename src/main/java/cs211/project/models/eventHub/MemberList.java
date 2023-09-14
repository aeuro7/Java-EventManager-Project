package cs211.project.models.eventHub;

import java.util.ArrayList;

public class MemberList {
    private ArrayList<Member> membershipsList;

    public MemberList() {
        membershipsList = new ArrayList<>();
    }
    public void addMember(Member member) {
        membershipsList.add(member);
    }
    public void addMember(String name, String eventID) {
        Member member = new Member(name, eventID, "AUDIENCE");
        membershipsList.add(member);
    }
    public void addMember(String name, String eventID, String role) {
        Member member = new Member(name, eventID, role);
        membershipsList.add(member);
    }
    public ArrayList<Member> getMemberList() {
        return membershipsList;
    }
}