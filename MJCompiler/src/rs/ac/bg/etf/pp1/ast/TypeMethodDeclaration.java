// generated with ast extension for cup
// version 0.8
// 16/7/2020 12:3:18


package rs.ac.bg.etf.pp1.ast;

public class TypeMethodDeclaration extends MethodDeclaration {

    private MethodTypeName MethodTypeName;
    private MethodDeclarationActual MethodDeclarationActual;

    public TypeMethodDeclaration (MethodTypeName MethodTypeName, MethodDeclarationActual MethodDeclarationActual) {
        this.MethodTypeName=MethodTypeName;
        if(MethodTypeName!=null) MethodTypeName.setParent(this);
        this.MethodDeclarationActual=MethodDeclarationActual;
        if(MethodDeclarationActual!=null) MethodDeclarationActual.setParent(this);
    }

    public MethodTypeName getMethodTypeName() {
        return MethodTypeName;
    }

    public void setMethodTypeName(MethodTypeName MethodTypeName) {
        this.MethodTypeName=MethodTypeName;
    }

    public MethodDeclarationActual getMethodDeclarationActual() {
        return MethodDeclarationActual;
    }

    public void setMethodDeclarationActual(MethodDeclarationActual MethodDeclarationActual) {
        this.MethodDeclarationActual=MethodDeclarationActual;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodTypeName!=null) MethodTypeName.accept(visitor);
        if(MethodDeclarationActual!=null) MethodDeclarationActual.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodTypeName!=null) MethodTypeName.traverseTopDown(visitor);
        if(MethodDeclarationActual!=null) MethodDeclarationActual.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodTypeName!=null) MethodTypeName.traverseBottomUp(visitor);
        if(MethodDeclarationActual!=null) MethodDeclarationActual.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TypeMethodDeclaration(\n");

        if(MethodTypeName!=null)
            buffer.append(MethodTypeName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclarationActual!=null)
            buffer.append(MethodDeclarationActual.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TypeMethodDeclaration]");
        return buffer.toString();
    }
}
