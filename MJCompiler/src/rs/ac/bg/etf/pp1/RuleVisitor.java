package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;

public class RuleVisitor extends VisitorAdaptor{

    int printCallCount = 0;
    int varDeclCount = 0;
    int constDeclCount = 0;
    int classDeclCount = 0;
    int methCallCount = 0;

    Logger log = Logger.getLogger(getClass());

    public void visit(Ident vardecl){ varDeclCount++; }

    public void visit(ConstDeclarationActual constdecl) {
        constDeclCount++;
    }

    public void visit(ClassDeclaration classdecl) {
        classDeclCount++;
    }

    public void visit(PrintExpressionStatement print) {
        printCallCount++;
    }

    public void visit(PrintExpressionNumStatement print) {
        printCallCount++;
    }

    public void visit(MethodCallNoParams print) {
        methCallCount++;
    }

    public void visit(MethodCallActualParams print) {
        methCallCount++;
    }

}