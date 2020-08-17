package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.Stack;


public class CodeGenerator extends VisitorAdaptor {
    private int mainPc;

    private boolean returned = false;
    private Stack<Obj> assignments = new Stack<Obj>();
    private Stack<Obj> designators = new Stack<Obj>();

    private void storeDesignator(Obj designatorObj){
        if (designatorObj.getKind() != Obj.Elem)
            Code.store(designatorObj);
        else if (designatorObj.getType() == Tab.intType)
            Code.put(Code.astore);
        else
            Code.put(Code.bastore);
    }
    private void loadDesignator(Obj designatorObj){
        if (designatorObj.getKind() != Obj.Elem)
            Code.load(designatorObj);
        else if (designatorObj.getType() == Tab.intType)
            Code.put(Code.aload);
        else
            Code.put(Code.baload);
    }
    private void doAssign(Obj asgn, Obj dst){
        switch (asgn.getName()) {
            case "+=": {
                Code.put(Code.add);
                break;}
            case "-=": {
                Code.put(Code.sub);
                break;}
            case "*=": {
                Code.put(Code.mul);
                break;}
            case "/=": {
                Code.put(Code.div);
                break;}
            case "%=": {
                Code.put(Code.rem);
                break;}
        }
        if(dst.getKind() != Obj.Elem) Code.put(Code.dup);
        else Code.put(Code.dup_x2);
        storeDesignator(dst);
        if(asgn.getName().equals("=") && dst.getType().getKind() != Struct.Array && dst.getKind() != Obj.Elem)
            Code.put(Code.pop);
    }
    public int getMainPc(){
        return mainPc;
    }

    public void visit(PrintExpressionStatement printStmt){
        if(printStmt.getExpression().struct == Tab.charType){
            Code.loadConst(1);
            Code.put(Code.bprint);
        } else {
            Code.loadConst(5);
            Code.put(Code.print);
        }
    }
    public void visit(PrintExpressionNumStatement printNumStmt){
        Code.load(new Obj(Obj.Con, "", Tab.intType, printNumStmt.getN(), 0));
        if(printNumStmt.getExpression().struct == Tab.charType){
            Code.put(Code.bprint);
        } else {
            Code.put(Code.print);
        }
    }
    public void visit(ReadStatement readStatement){
        if (readStatement.getDesignator().obj.getType() == Tab.charType) {
            Code.put(Code.bread);
        }
        else {
            Code.put(Code.read);
        }
        storeDesignator(readStatement.getDesignator().obj);
    }

    // load const
    public void visit(NumberConst numberConst){
        SyntaxNode node = numberConst.getParent();
        if(node.getClass() != ConstDeclarationActual.class){
            Obj con = Tab.insert(Obj.Con, "$", numberConst.struct);
            con.setLevel(0);
            con.setAdr(numberConst.getVal());
            Code.load(con);
        } else {
            Obj con = Tab.find(node.getClass().getName());
            Code.load(con);
        }
    }
    public void visit(BoolConst boolConst){
        SyntaxNode node = boolConst.getParent();
        if(node.getClass() != ConstDeclarationActual.class){
            Obj con = Tab.insert(Obj.Con, "$", boolConst.struct);
            con.setLevel(0);
            con.setAdr(boolConst.getVal());
            Code.load(con);
        } else {
            Obj con = Tab.find(node.getClass().getName());
            Code.load(con);
        }
    }
    public void visit(CharConst charConst){
        SyntaxNode node = charConst.getParent();
        if(node.getClass() != ConstDeclarationActual.class){
            Obj con = Tab.insert(Obj.Con, "$", charConst.struct);
            con.setLevel(0);
            con.setAdr(charConst.getVal());
            Code.load(con);
        } else {
            Obj con = Tab.find(node.getClass().getName());
            Code.load(con);
        }
    }

    // assignments
    public void visit(LeftDesignatorAssignExpression ldAssignExpression){
        doAssign(ldAssignExpression.getAssignOps().obj, designators.pop());
        while (!assignments.empty()){
            //loadDesignator(designators.pop());
            doAssign(assignments.pop(),designators.pop());
        }
        if(!designators.empty()) designators.pop();
        Code.put(Code.pop);
    }
    public void visit(LeftSideAssignAssignOps leftSideAssignAssignOps){
        assignments.push(leftSideAssignAssignOps.getAssignOps().obj);
        designators.push(leftSideAssignAssignOps.getDesignator().obj);
        if(leftSideAssignAssignOps.getDesignator().obj.getKind() == Obj.Elem) Code.put(Code.dup2);
        loadDesignator(leftSideAssignAssignOps.getDesignator().obj);
    }
    public void visit(LeftSideAssignVar leftSideAssignVar){
        if(leftSideAssignVar.obj.getKind() == Obj.Elem) Code.put(Code.dup2);
        loadDesignator(leftSideAssignVar.obj);
        designators.push(leftSideAssignVar.obj);
    }
    public void visit(AssignOpEqual assignOpEqual){
        if(assignOpEqual.getParent().getClass() == LeftSideAssignAssignOps.class ||
                assignOpEqual.getParent().getClass() == LeftDesignatorAssignExpression.class){
            if(designators.peek().getKind() == Obj.Elem) Code.put(Code.pop);
        }
    }
    public void visit(DesignatorStatementIncrement dStatementIncrement){
        if(dStatementIncrement.getDesignator().obj.getKind() == Obj.Elem){
            Code.put(Code.dup2);
        }
        loadDesignator(dStatementIncrement.getDesignator().obj);
        Code.loadConst(1);
        Code.put(Code.add);
        storeDesignator(dStatementIncrement.getDesignator().obj);
    }
    public void visit(DesignatorStatementDecrement dStatementDecrement){
        if(dStatementDecrement.getDesignator().obj.getKind() == Obj.Elem){
            Code.put(Code.dup2);
        }
        loadDesignator(dStatementDecrement.getDesignator().obj);
        Code.loadConst(1);
        Code.put(Code.sub);
        storeDesignator(dStatementDecrement.getDesignator().obj);
    }

    // factor
    public void visit(MinusTerm minusTerm){
        Code.put(Code.neg);
    }
    public void visit(FactorDesignator fDesignator){
        loadDesignator(fDesignator.getDesignator().obj);
    }
    public void visit(ArrayAccess arrayAccess){
        loadDesignator(arrayAccess.obj);
    }
    // ops
    public void visit(TermMullOpLeftList termMullOpLeftList){
        if(termMullOpLeftList.getMulOpLeft().getClass() == MulOpLeftMul.class){
            Code.put(Code.mul);
            return;
        }
        if(termMullOpLeftList.getMulOpLeft().getClass() == MulOpLeftDiv.class){
            Code.put(Code.div);
            return;
        }
        if(termMullOpLeftList.getMulOpLeft().getClass() == MulOpLeftMod.class){
            Code.put(Code.rem);
        }
    }
    public void visit(ExpressionAddopLeftList expressionAddopLeftList){
        if(expressionAddopLeftList.getAddOpLeft().getClass() == AddOpLeftPlus.class){
            Code.put(Code.add);
            return;
        }
        if(expressionAddopLeftList.getAddOpLeft().getClass() == AddOpLeftMinus.class){
            Code.put(Code.sub);
        }
    }

    // method type name
    public void visit(MethodTypeName methodTypeName){
        methodTypeName.obj.setAdr(Code.pc);
        // Collect arguments and local variables
        SyntaxNode methodNode = methodTypeName.getParent();
        CounterVisitor.VarCounter varCnt = new CounterVisitor.VarCounter();
        methodNode.traverseTopDown(varCnt);
        CounterVisitor.FormParamCounter fpCnt = new CounterVisitor.FormParamCounter();
        methodNode.traverseTopDown(fpCnt);

        // Generate the entry
        Code.put(Code.enter);
        Code.put(fpCnt.getCount());
        Code.put(fpCnt.getCount() + varCnt.getCount());
        returned = false;
    }
    public void visit(MethodVoidName methodVoidName){
        if("main".equalsIgnoreCase(methodVoidName.getMethodName())){
            mainPc = Code.pc;
        }
        methodVoidName.obj.setAdr(Code.pc);
        // Collect arguments and local variables
        SyntaxNode methodNode = methodVoidName.getParent();
        CounterVisitor.VarCounter varCnt = new CounterVisitor.VarCounter();
        methodNode.traverseTopDown(varCnt);
        CounterVisitor.FormParamCounter fpCnt = new CounterVisitor.FormParamCounter();
        methodNode.traverseTopDown(fpCnt);

        // Generate the entry
        Code.put(Code.enter);
        Code.put(fpCnt.getCount());
        Code.put(fpCnt.getCount() + varCnt.getCount());
        returned = false;
    }
    public void visit(VoidMethodDeclaration voidMethodDeclaration){
        if(returned) return;
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    // method call
    public void visit(MethodCallActualParams methodCallActualParams){
        Obj functionObj = methodCallActualParams.getDesignator().obj;
        int offset = functionObj.getAdr() - Code.pc;
        Code.put(Code.call);
        Code.put2(offset);
        returned = false;
    }
    public void visit(MethodCallNoParams methodCallNoParams){
        Obj functionObj = methodCallNoParams.getDesignator().obj;
        int offset = functionObj.getAdr() - Code.pc;
        Code.put(Code.call);
        Code.put2(offset);
        returned = false;
    }

    // return
    public void visit(ReturnStatementExpression returnExpr){
        // main only -> void?
        Code.put(Code.exit);
        Code.put(Code.return_);
    }
    public void visit(ReturnStatementEmpty returnNoExpr){
        Code.put(Code.exit);
        Code.put(Code.return_);
        returned = true;
    }

    // new array
    public void visit(FactorNewTypeExpression factorNewTypeExpression){
        Code.put(Code.newarray);
        if (factorNewTypeExpression.getType().struct == Tab.charType) {
            Code.put(0);
        } else {
            Code.put(1);
        }
    }
}
