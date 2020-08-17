// generated with ast extension for cup
// version 0.8
// 18/7/2020 0:52:17


package rs.ac.bg.etf.pp1.ast;

public class PrintExpressionNumStatement extends Statement {

    private Expression Expression;
    private Integer n;

    public PrintExpressionNumStatement (Expression Expression, Integer n) {
        this.Expression=Expression;
        if(Expression!=null) Expression.setParent(this);
        this.n=n;
    }

    public Expression getExpression() {
        return Expression;
    }

    public void setExpression(Expression Expression) {
        this.Expression=Expression;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n=n;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expression!=null) Expression.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expression!=null) Expression.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expression!=null) Expression.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintExpressionNumStatement(\n");

        if(Expression!=null)
            buffer.append(Expression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+n);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintExpressionNumStatement]");
        return buffer.toString();
    }
}
