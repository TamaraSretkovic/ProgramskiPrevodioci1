// generated with ast extension for cup
// version 0.8
// 18/7/2020 0:13:41


package rs.ac.bg.etf.pp1.ast;

public class VoidMethodDeclaration extends MethodDeclaration {

    private MethodVoidName MethodVoidName;
    private MethodDeclarationActual MethodDeclarationActual;

    public VoidMethodDeclaration (MethodVoidName MethodVoidName, MethodDeclarationActual MethodDeclarationActual) {
        this.MethodVoidName=MethodVoidName;
        if(MethodVoidName!=null) MethodVoidName.setParent(this);
        this.MethodDeclarationActual=MethodDeclarationActual;
        if(MethodDeclarationActual!=null) MethodDeclarationActual.setParent(this);
    }

    public MethodVoidName getMethodVoidName() {
        return MethodVoidName;
    }

    public void setMethodVoidName(MethodVoidName MethodVoidName) {
        this.MethodVoidName=MethodVoidName;
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
        if(MethodVoidName!=null) MethodVoidName.accept(visitor);
        if(MethodDeclarationActual!=null) MethodDeclarationActual.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodVoidName!=null) MethodVoidName.traverseTopDown(visitor);
        if(MethodDeclarationActual!=null) MethodDeclarationActual.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodVoidName!=null) MethodVoidName.traverseBottomUp(visitor);
        if(MethodDeclarationActual!=null) MethodDeclarationActual.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VoidMethodDeclaration(\n");

        if(MethodVoidName!=null)
            buffer.append(MethodVoidName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclarationActual!=null)
            buffer.append(MethodDeclarationActual.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VoidMethodDeclaration]");
        return buffer.toString();
    }
}
