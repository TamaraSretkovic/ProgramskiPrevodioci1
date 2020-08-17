// generated with ast extension for cup
// version 0.8
// 16/7/2020 12:3:18


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl extends ConstDeclarationList {

    private ConstDeclarationActual ConstDeclarationActual;

    public ConstDecl (ConstDeclarationActual ConstDeclarationActual) {
        this.ConstDeclarationActual=ConstDeclarationActual;
        if(ConstDeclarationActual!=null) ConstDeclarationActual.setParent(this);
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
        if(ConstDeclarationActual!=null) ConstDeclarationActual.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclarationActual!=null) ConstDeclarationActual.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclarationActual!=null) ConstDeclarationActual.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(ConstDeclarationActual!=null)
            buffer.append(ConstDeclarationActual.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
