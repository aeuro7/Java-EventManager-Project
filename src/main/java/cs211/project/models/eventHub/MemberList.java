package cs211.project.models.eventHub;

import java.util.ArrayList;

public class MemberList {
    private ArrayList<Member> memberList;

    public MemberList() {
        memberList = new ArrayList<>();
    }
    public void addMember(Member member) {
        memberList.add(member);
    }
    public void addMember(String name, String eventID) {
        boolean check = true;
        for(Member member: memberList) {
            if(member.getUsername().equals(name) && member.getEventID().equals(eventID)) {
                check = false;
            }
        }
        if(check) {
            Member newMember = new Member(name, eventID, "AUDIENCE");
            memberList.add(newMember);
        }
    }
    public void addMember(String name, String eventID, String role) {
        Member member = new Member(name, eventID, role);
        memberList.add(member);
    }
    public ArrayList<Member> getMemberList() {
        return memberList;
    }
}