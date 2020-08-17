// generated with ast extension for cup
// version 0.8
// 16/7/2020 12:3:18


package rs.ac.bg.etf.pp1.ast;

public class TermMullOpLeftList extends Term {

    private Term Term;
    private MulOpLeft MulOpLeft;
    private Factor Factor;

    public TermMullOpLeftList (Term Term, MulOpLeft MulOpLeft, Factor Factor) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.MulOpLeft=MulOpLeft;
        if(MulOpLeft!=null) MulOpLeft.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public MulOpLeft getMulOpLeft() {
        return MulOpLeft;
    }

    public void setMulOpLeft(MulOpLeft MulOpLeft) {
        this.MulOpLeft=MulOpLeft;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(MulOpLeft!=null) MulOpLeft.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(MulOpLeft!=null) MulOpLeft.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(MulOpLeft!=null) MulOpLeft.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermMullOpLeftList(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MulOpLeft!=null)
            buffer.append(MulOpLeft.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermMullOpLeftList]");
        return buffer.toString();
    }
}
