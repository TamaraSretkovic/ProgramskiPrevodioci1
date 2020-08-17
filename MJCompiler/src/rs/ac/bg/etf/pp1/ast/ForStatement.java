// generated with ast extension for cup
// version 0.8
// 17/7/2020 23:9:50


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private DesignatorStatementList DesignatorStatementList;
    private ConditionOptional ConditionOptional;
    private DesignatorStatementList DesignatorStatementList1;
    private Statement Statement;

    public ForStatement (DesignatorStatementList DesignatorStatementList, ConditionOptional ConditionOptional, DesignatorStatementList DesignatorStatementList1, Statement Statement) {
        this.DesignatorStatementList=DesignatorStatementList;
        if(DesignatorStatementList!=null) DesignatorStatementList.setParent(this);
        this.ConditionOptional=ConditionOptional;
        if(ConditionOptional!=null) ConditionOptional.setParent(this);
        this.DesignatorStatementList1=DesignatorStatementList1;
        if(DesignatorStatementList1!=null) DesignatorStatementList1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public DesignatorStatementList getDesignatorStatementList() {
        return DesignatorStatementList;
    }

    public void setDesignatorStatementList(DesignatorStatementList DesignatorStatementList) {
        this.DesignatorStatementList=DesignatorStatementList;
    }

    public ConditionOptional getConditionOptional() {
        return ConditionOptional;
    }

    public void setConditionOptional(ConditionOptional ConditionOptional) {
        this.ConditionOptional=ConditionOptional;
    }

    public DesignatorStatementList getDesignatorStatementList1() {
        return DesignatorStatementList1;
    }

    public void setDesignatorStatementList1(DesignatorStatementList DesignatorStatementList1) {
        this.DesignatorStatementList1=DesignatorStatementList1;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatementList!=null) DesignatorStatementList.accept(visitor);
        if(ConditionOptional!=null) ConditionOptional.accept(visitor);
        if(DesignatorStatementList1!=null) DesignatorStatementList1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementList!=null) DesignatorStatementList.traverseTopDown(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseTopDown(visitor);
        if(DesignatorStatementList1!=null) DesignatorStatementList1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementList!=null) DesignatorStatementList.traverseBottomUp(visitor);
        if(ConditionOptional!=null) ConditionOptional.traverseBottomUp(visitor);
        if(DesignatorStatementList1!=null) DesignatorStatementList1.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(DesignatorStatementList!=null)
            buffer.append(DesignatorStatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionOptional!=null)
            buffer.append(ConditionOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatementList1!=null)
            buffer.append(DesignatorStatementList1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStatement]");
        return buffer.toString();
    }
}
