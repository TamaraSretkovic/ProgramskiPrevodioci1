package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import sun.reflect.generics.tree.FormalTypeParameter;

public class SemanticAnalizer extends VisitorAdaptor{

    int varDeclCount = 0;
    int constDeclCount = 0;
    int arrayDeclCount = 0;
    int methDeclCount = 0;

    int localsInMain = 0;
    int statementsInMain = 0;

    boolean inMain = false;
    boolean inMeth = false;

    Logger log = Logger.getLogger(getClass());

    public void visit(Ident vardecl){ if(!inMeth) varDeclCount++;  if(inMain) localsInMain++;}
    public void visit(IdentArray vardecl){ if(!inMeth) arrayDeclCount++;  if(inMain) localsInMain++;}
    public void visit(ConstDeclarationActual constdecl) {
        if(!inMeth) constDeclCount++;
    }


    public void visit(TypeMethodDeclaration typeMethodDeclaration) { methDeclCount++; inMain = false; inMeth = false;}
    public void visit(VoidMethodDeclaration voidMethodDeclaration) { methDeclCount++; inMain = false; inMeth = false;}


    public void visit(MethodVoidName methodVoidName){
        if(methodVoidName.getMethodName().equalsIgnoreCase("main")) inMain = true;
        inMeth = true;
    }
    public void visit(MethodTypeName methodVoidName){
        inMeth = true;
    }


    public void visit(IfStatement IfStatement){ if(inMain) statementsInMain++; }
    public void visit(DesignatorStatementStatement IfStatement){ if(inMain) statementsInMain++; }
    public void visit(ReadStatement IfStatement){ if(inMain) statementsInMain++; }
    public void visit(PrintExpressionStatement IfStatement){ if(inMain) statementsInMain++; }
    public void visit(PrintExpressionNumStatement IfStatement){ if(inMain) statementsInMain++; }
    public void visit(ForStatement IfStatement){ if(inMain) statementsInMain++; }
    public void visit(ForeachStatement IfStatement){ if(inMain) statementsInMain++; }
    public void visit(ContinueStatement IfStatement){ if(inMain) statementsInMain++; }
    public void visit(ReturnStatementEmpty IfStatement){ if(inMain) statementsInMain++; }
    public void visit(ReturnStatementExpression IfStatement){ if(inMain) statementsInMain++; }
    public void visit(CurlyBraceStatement IfStatement){ if(inMain) statementsInMain++; }
}