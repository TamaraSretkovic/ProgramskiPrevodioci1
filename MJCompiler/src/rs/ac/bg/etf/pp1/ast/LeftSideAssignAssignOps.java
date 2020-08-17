// generated with ast extension for cup
// version 0.8
// 18/7/2020 0:13:41


package rs.ac.bg.etf.pp1.ast;

public class LeftSideAssignAssignOps extends LeftSideAssign {

    private LeftSideAssign LeftSideAssign;
    private AssignOps AssignOps;
    private Designator Designator;

    public LeftSideAssignAssignOps (LeftSideAssign LeftSideAssign, AssignOps AssignOps, Designator Designator) {
        this.LeftSideAssign=LeftSideAssign;
        if(LeftSideAssign!=null) LeftSideAssign.setParent(this);
        this.AssignOps=AssignOps;
        if(AssignOps!=null) AssignOps.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public LeftSideAssign getLeftSideAssign() {
        return LeftSideAssign;
    }

    public void setLeftSideAssign(LeftSideAssign LeftSideAssign) {
        this.LeftSideAssign=LeftSideAssign;
    }

    public AssignOps getAssignOps() {
        return AssignOps;
    }

    public void setAssignOps(AssignOps AssignOps) {
        this.AssignOps=AssignOps;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(LeftSideAssign!=null) LeftSideAssign.accept(visitor);
        if(AssignOps!=null) AssignOps.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LeftSideAssign!=null) LeftSideAssign.traverseTopDown(visitor);
        if(AssignOps!=null) AssignOps.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LeftSideAssign!=null) LeftSideAssign.traverseBottomUp(visitor);
        if(AssignOps!=null) AssignOps.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("LeftSideAssignAssignOps(\n");

        if(LeftSideAssign!=null)
            buffer.append(LeftSideAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AssignOps!=null)
            buffer.append(AssignOps.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [LeftSideAssignAssignOps]");
        return buffer.toString();
    }
}
