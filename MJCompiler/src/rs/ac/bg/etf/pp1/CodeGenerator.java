package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;


public class CodeGenerator extends VisitorAdaptor {
    private int mainPc;

    private boolean returned = false;

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
        if(printNumStmt.getExpression().struct == Tab.intType
                || printNumStmt.getExpression().struct.getKind() == Struct.Bool){
            Code.loadConst(5);
            Code.put(Code.print);
        }else if(printNumStmt.getExpression().struct == Tab.charType){
            Code.loadConst(1);
            Code.put(Code.bprint);
        }
    } // ???
    public void visit(ReadStatement readStatement){
        if (readStatement.getDesignator().obj.getType() == Tab.charType) {
            Code.put(Code.bread);
        }
        else {
            Code.put(Code.read);
        }
        Code.store(readStatement.getDesignator().obj);
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

    // load designator

    public void visit(LeftDesignatorAssignExpression ldAssignExpression){
        // TODO koji assign?

        //if(ldAssignExpression.getLeftSideAssign().obj.getKind() == Obj.Elem){
        //    ldAssignExpression.getLeftSideAssign().obj = new Obj(Obj.Elem, "",
        //            ldAssignExpression.getLeftSideAssign().obj.getType().getElemType());
        //    if(ldAssignExpression.getLeftSideAssign().obj.getType().getElemType() == Tab.charType) {
        //        Code.put(Code.bastore);
        //    } else Code.put(Code.astore);
        //} else
            Code.store(ldAssignExpression.getLeftSideAssign().obj);
    }
    public void visit(DesignatorStatementIncrement dStatementIncrement){
        if(dStatementIncrement.getDesignator().obj.getKind() == Obj.Elem){
            Code.put(Code.dup2);
        }
        Code.load(dStatementIncrement.getDesignator().obj);
        Code.loadConst(1);
        Code.put(Code.add);
        Code.store(dStatementIncrement.getDesignator().obj);
    }
    public void visit(DesignatorStatementDecrement dStatementDecrement){
        if(dStatementDecrement.getDesignator().obj.getKind() == Obj.Elem){
            Code.put(Code.dup2);
        }
        Code.load(dStatementDecrement.getDesignator().obj);
        Code.loadConst(1);
        Code.put(Code.sub);
        Code.store(dStatementDecrement.getDesignator().obj);
    }
    public void visit(DesignatorOnly designatorOnly){
       // Code.load(designatorOnly.obj);
    }
    public void visit(DesignatorArray designatorArray){
        designatorArray.obj = Tab.find(designatorArray.getName());
        Code.load(designatorArray.obj);
    }
    // factor
    public void visit(MinusTerm minusTerm){
        Code.put(Code.neg);
    }
    public void visit(FactorDesignator fDesignator){
        //if(fDesignator.getDesignator().obj.getKind() == Obj.Elem){
        //    fDesignator.getDesignator().obj = new Obj(Obj.Elem, "",
        //            fDesignator.getDesignator().obj.getType().getElemType());
        //    if(fDesignator.getDesignator().obj.getType().getElemType() == Tab.charType) {
        //        Code.put(Code.baload);
        //    } else Code.put(Code.aload);
        //} else
        Code.load(fDesignator.getDesignator().obj);
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
    // pomocne fje
}
