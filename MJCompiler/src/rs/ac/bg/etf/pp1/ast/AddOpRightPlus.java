// generated with ast extension for cup
// version 0.8
// 17/7/2020 23:9:50


package rs.ac.bg.etf.pp1.ast;

public class AddOpRightPlus extends AddOpRight {

    public AddOpRightPlus () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddOpRightPlus(\n");

        buffer.append(tab);
        buffer.append(") [AddOpRightPlus]");
        return buffer.toString();
    }
}
