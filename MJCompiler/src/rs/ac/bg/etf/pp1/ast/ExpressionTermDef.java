// generated with ast extension for cup
// version 0.8
// 16/7/2020 12:3:18


package rs.ac.bg.etf.pp1.ast;

public class ExpressionTermDef extends Expression {

    private TermDef TermDef;

    public ExpressionTermDef (TermDef TermDef) {
        this.TermDef=TermDef;
        if(TermDef!=null) TermDef.setParent(this);
    }

    public TermDef getTermDef() {
        return TermDef;
    }

    public void setTermDef(TermDef TermDef) {
        this.TermDef=TermDef;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TermDef!=null) TermDef.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermDef!=null) TermDef.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermDef!=null) TermDef.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExpressionTermDef(\n");

        if(TermDef!=null)
            buffer.append(TermDef.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExpressionTermDef]");
        return buffer.toString();
    }
}
