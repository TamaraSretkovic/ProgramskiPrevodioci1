// generated with ast extension for cup
// version 0.8
// 16/7/2020 12:3:18


package rs.ac.bg.etf.pp1.ast;

public class ExpressionAddopLeftList extends Expression {

    private Expression Expression;
    private AddOpLeft AddOpLeft;
    private Term Term;

    public ExpressionAddopLeftList (Expression Expression, AddOpLeft AddOpLeft, Term Term) {
        this.Expression=Expression;
        if(Expression!=null) Expression.setParent(this);
        this.AddOpLeft=AddOpLeft;
        if(AddOpLeft!=null) AddOpLeft.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public Expression getExpression() {
        return Expression;
    }

    public void setExpression(Expression Expression) {
        this.Expression=Expression;
    }

    public AddOpLeft getAddOpLeft() {
        return AddOpLeft;
    }

    public void setAddOpLeft(AddOpLeft AddOpLeft) {
        this.AddOpLeft=AddOpLeft;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expression!=null) Expression.accept(visitor);
        if(AddOpLeft!=null) AddOpLeft.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expression!=null) Expression.traverseTopDown(visitor);
        if(AddOpLeft!=null) AddOpLeft.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expression!=null) Expression.traverseBottomUp(visitor);
        if(AddOpLeft!=null) AddOpLeft.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExpressionAddopLeftList(\n");

        if(Expression!=null)
            buffer.append(Expression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddOpLeft!=null)
            buffer.append(AddOpLeft.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExpressionAddopLeftList]");
        return buffer.toString();
    }
}
