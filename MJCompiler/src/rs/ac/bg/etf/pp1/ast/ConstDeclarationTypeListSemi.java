// generated with ast extension for cup
// version 0.8
// 16/7/2020 12:3:18


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclarationTypeListSemi extends ConstDeclaration {

    private VarType VarType;
    private ConstDeclarationList ConstDeclarationList;

    public ConstDeclarationTypeListSemi (VarType VarType, ConstDeclarationList ConstDeclarationList) {
        this.VarType=VarType;
        if(VarType!=null) VarType.setParent(this);
        this.ConstDeclarationList=ConstDeclarationList;
        if(ConstDeclarationList!=null) ConstDeclarationList.setParent(this);
    }

    public VarType getVarType() {
        return VarType;
    }

    public void setVarType(VarType VarType) {
        this.VarType=VarType;
    }

    public ConstDeclarationList getConstDeclarationList() {
        return ConstDeclarationList;
    }

    public void setConstDeclarationList(ConstDeclarationList ConstDeclarationList) {
        this.ConstDeclarationList=ConstDeclarationList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarType!=null) VarType.accept(visitor);
        if(ConstDeclarationList!=null) ConstDeclarationList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarType!=null) VarType.traverseTopDown(visitor);
        if(ConstDeclarationList!=null) ConstDeclarationList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarType!=null) VarType.traverseBottomUp(visitor);
        if(ConstDeclarationList!=null) ConstDeclarationList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarationTypeListSemi(\n");

        if(VarType!=null)
            buffer.append(VarType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclarationList!=null)
            buffer.append(ConstDeclarationList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarationTypeListSemi]");
        return buffer.toString();
    }
}
