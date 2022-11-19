package yote.workschedule.Model;

import java.util.ArrayList;

public class MemberList {
    private ArrayList<Member> memberList;

    public MemberList()
    {
        memberList = new ArrayList<>();
    }

    public void add (Member member)
    {
        memberList.add(member);
    }
    public ArrayList<Member> getMembersList()
    {
        return memberList;
    }

    public String toCsv() {
        String result = "";
        for (Member product: memberList) {
            result += product.toCsv() + "\n";
        }
        return result;
    }

    public void add(String name, int c_id, String userName, String password, String phone) {
        Member member = new Member(name, c_id, userName,password,phone);
        memberList.add(member);
    }
}
