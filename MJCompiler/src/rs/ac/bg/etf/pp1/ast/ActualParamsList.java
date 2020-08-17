// generated with ast extension for cup
// version 0.8
// 17/7/2020 23:9:50


package rs.ac.bg.etf.pp1.ast;

public class ActualParamsList extends ActualParams {

    private ActualParams ActualParams;
    private Expression Expression;

    public ActualParamsList (ActualParams ActualParams, Expression Expression) {
        this.ActualParams=ActualParams;
        if(ActualParams!=null) ActualParams.setParent(this);
        this.Expression=Expression;
        if(Expression!=null) Expression.setParent(this);
    }

    public ActualParams getActualParams() {
        return ActualParams;
    }

    public void setActualParams(ActualParams ActualParams) {
        this.ActualParams=ActualParams;
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
        if(ActualParams!=null) ActualParams.accept(visitor);
        if(Expression!=null) Expression.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParams!=null) ActualParams.traverseTopDown(visitor);
        if(Expression!=null) Expression.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParams!=null) ActualParams.traverseBottomUp(visitor);
        if(Expression!=null) Expression.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParamsList(\n");

        if(ActualParams!=null)
            buffer.append(ActualParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expression!=null)
            buffer.append(Expression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParamsList]");
        return buffer.toString();
    }
}
