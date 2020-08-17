// generated with ast extension for cup
// version 0.8
// 18/7/2020 0:13:41


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclarationListComaActual extends ConstDeclarationList {

    private ConstDeclarationList ConstDeclarationList;
    private ConstDeclarationActual ConstDeclarationActual;

    public ConstDeclarationListComaActual (ConstDeclarationList ConstDeclarationList, ConstDeclarationActual ConstDeclarationActual) {
        this.ConstDeclarationList=ConstDeclarationList;
        if(ConstDeclarationList!=null) ConstDeclarationList.setParent(this);
        this.ConstDeclarationActual=ConstDeclarationActual;
        if(ConstDeclarationActual!=null) ConstDeclarationActual.setParent(this);
    }

    public ConstDeclarationList getConstDeclarationList() {
        return ConstDeclarationList;
    }

    public void setConstDeclarationList(ConstDeclarationList ConstDeclarationList) {
        this.ConstDeclarationList=ConstDeclarationList;
    }

    public ConstDeclarationActual getConstDeclarationActual() {
        return ConstDeclarationActual;
    }

    public void setConstDeclarationActual(ConstDeclarationActual ConstDeclarationActual) {
        this.ConstDeclarationActual=ConstDeclarationActual;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclarationList!=null) ConstDeclarationList.accept(visitor);
        if(ConstDeclarationActual!=null) ConstDeclarationActual.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclarationList!=null) ConstDeclarationList.traverseTopDown(visitor);
        if(ConstDeclarationActual!=null) ConstDeclarationActual.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclarationList!=null) ConstDeclarationList.traverseBottomUp(visitor);
        if(ConstDeclarationActual!=null) ConstDeclarationActual.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarationListComaActual(\n");

        if(ConstDeclarationList!=null)
            buffer.append(ConstDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclarationActual!=null)
            buffer.append(ConstDeclarationActual.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarationListComaActual]");
        return buffer.toString();
    }
}
