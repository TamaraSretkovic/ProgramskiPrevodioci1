// generated with ast extension for cup
// version 0.8
// 18/7/2020 0:52:17


package rs.ac.bg.etf.pp1.ast;

public class MulOpLeftMod extends MulOpLeft {

    public MulOpLeftMod () {
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
        buffer.append("MulOpLeftMod(\n");

        buffer.append(tab);
        buffer.append(") [MulOpLeftMod]");
        return buffer.toString();
    }
}
