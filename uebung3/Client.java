package org.hbrs.s1.ws22.uebung3;

public class Client {
    private Container c;

    public Client (Container c) {
        this.c = c;
    }

    public void addMember (Member m) {
        try {
            c.addMember(m);
        } catch (ContainerException e) {
            System.out.println(e.getMessage());
        }
    }

    public Member createMember(Integer id) {
        Member m = new ConcreteMember(id);
        return m;
    }

    public void toDump() {
        MemberView mv = new MemberView();
        mv.dump(c.getCurrentList());
    }

    public Container getContainer () {
        return c;
    }
}
