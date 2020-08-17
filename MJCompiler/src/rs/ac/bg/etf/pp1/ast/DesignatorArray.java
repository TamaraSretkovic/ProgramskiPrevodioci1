// generated with ast extension for cup
// version 0.8
// 18/7/2020 0:13:41


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArray extends Designator {

    private ArrayAccess ArrayAccess;
    private Expression Expression;

    public DesignatorArray (ArrayAccess ArrayAccess, Expression Expression) {
        this.ArrayAccess=ArrayAccess;
        if(ArrayAccess!=null) ArrayAccess.setParent(this);
        this.Expression=Expression;
        if(Expression!=null) Expression.setParent(this);
    }

    public ArrayAccess getArrayAccess() {
        return ArrayAccess;
    }

    public void setArrayAccess(ArrayAccess ArrayAccess) {
        this.ArrayAccess=ArrayAccess;
    }

    public Expression getExpression() {
        return Expression;
    }

    public void setExpression(Expression Expression) {
        this.Expression=Expression;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrayAccess!=null) ArrayAccess.accept(visitor);
        if(Expression!=null) Expression.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayAccess!=null) ArrayAccess.traverseTopDown(visitor);
        if(Expression!=null) Expression.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayAccess!=null) ArrayAccess.traverseBottomUp(visitor);
        if(Expression!=null) Expression.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArray(\n");

        if(ArrayAccess!=null)
            buffer.append(ArrayAccess.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expression!=null)
            buffer.append(Expression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArray]");
        return buffer.toString();
    }
}
