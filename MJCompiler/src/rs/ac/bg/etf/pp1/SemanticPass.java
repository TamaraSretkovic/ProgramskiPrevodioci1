package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.*;

public class SemanticPass extends VisitorAdaptor {

    public static final Struct boolType = new Struct(Struct.Bool);
    boolean errorDetected = false;

    int nVars;
    Struct declarationTypeNode = null;

    int constNum;
    boolean constBool;
    char constChar;

    Obj currentMethod = null;
    boolean returnFound = false;
    boolean mainMethodFound = false;
    int currentMethodFormParNum = 0;
    boolean insideForExpression = false;

    Logger log = Logger.getLogger(getClass());

    public void report_error(String message, SyntaxNode info) {
        errorDetected = true;
        StringBuilder msg = new StringBuilder(message);
        int line = (info == null) ? 0: info.getLine();
        if (line != 0)
            msg.append (" na liniji ").append(line);
        log.error(msg.toString());
    }
    public void report_info(String message, SyntaxNode info) {
        StringBuilder msg = new StringBuilder(message);
        int line = (info == null) ? 0: info.getLine();
        if (line != 0)
            msg.append (" na liniji ").append(line);
        log.info(msg.toString());
    }

    public static String printObj(Obj tableEntry) {
        StringBuilder output = new StringBuilder();
        switch (tableEntry.getKind()) {
            case Obj.Con:
                output.append("Con ");
                break;
            case Obj.Var:
                output.append("Var ");
                break;
            case Obj.Meth:
                output.append("Meth ");
                break;
        }

        output.append(tableEntry.getName());
        output.append(": ");

        switch (tableEntry.getType().getKind()) {
            case Struct.Bool:
                output.append("bool ");
                break;
            case Struct.Array:
                output.append("array");
                break;
            case Struct.Char:
                output.append("char");
                break;
            case Struct.Int:
                output.append("int");
                break;
            case Struct.None:
                output.append("none");
                break;
        }

        output.append(", ");
        output.append(tableEntry.getAdr());
        output.append(", ");
        output.append(tableEntry.getLevel() + " ");

        return output.toString();
    }

    // obrada programa
    public void visit(ProgName progName){
        progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
        Tab.openScope();
    }
    public void visit(Program program) {
        nVars = Tab.currentScope.getnVars();
        Tab.chainLocalSymbols(program.getProgName().obj);
        Tab.closeScope();
    }

    public void visit(VarType varType){
        if(isTypeValid(varType.getType())) {
            declarationTypeNode = varType.getType().struct;
        }
    }

    // obrada promenljivih
    public void visit(Ident ident){
        if(declarationTypeNode != null) {
            if(!tableHasIdent(ident.getName())) {
                Obj varNode = Tab.insert(Obj.Var, ident.getName(), declarationTypeNode);
                // set adress
                // varNode.setLevel(0);
                report_info("Deklarisana promenljiva " + ident.getName() + "(" + printObj(varNode) + ")", ident);
            }else {
                report_error("Pronadjena varijabla sa imenom " + ident.getName() + " u tabeli simbola! ", null);
            }
        }
    }
    public void visit(IdentArray identArray){
        if(declarationTypeNode != null) {
            if(!tableHasIdent(identArray.getName())) {
                Struct arrayStruct = new Struct(Struct.Array, declarationTypeNode);
                Obj array = Tab.insert(Obj.Var, identArray.getName(), arrayStruct);
                report_info("Deklarisan niz "+ identArray.getName() + "(" + printObj(array) + ")", identArray);
            } else {
                report_error("Pronadjena varijabla sa imenom " + identArray.getName() + " u tabeli simbola! ", null);
            }
        }
    }
    public void visit(VarDeclarationV varDeclarationV){
        declarationTypeNode = null;
    }

    // obrada konstanti
    public void visit(ConstDeclarationActual constDeclarationActual){
        if(!tableHasIdent(constDeclarationActual.getName())){
            if(!constDeclarationActual.getConst().struct.equals(declarationTypeNode)){
                report_error("Tip identifikatora konstante i tip izraza koji se dodeljuje se ne poklapaju", null);
            }
            boolean defaultCase = false;
            Obj con = null;
            switch (declarationTypeNode.getKind()){
                case Struct.Bool: {
                        con = Tab.insert(Obj.Con, constDeclarationActual.getName(), declarationTypeNode);
                        con.setAdr(constBool?1:0);
                    break;
                }
                case Struct.Char: {
                        con = Tab.insert(Obj.Con, constDeclarationActual.getName(), declarationTypeNode);
                        con.setAdr(constChar);
                    break;
                }
                case Struct.Int: {
                        con = Tab.insert(Obj.Con, constDeclarationActual.getName(), declarationTypeNode);
                        con.setAdr(constNum);
                    break;
                }
                default: {
                    report_error("Za konstante se mogu koristiti samo osnovni tipovi podataka!", null);
                    defaultCase = true;
                    break;
                }
            }
            if(!defaultCase){
                report_info("Deklarisana konstanta " + constDeclarationActual.getName() + "(" + printObj(con) + ")", constDeclarationActual);
            }
            declarationTypeNode = null;
        } else {
            report_error("Ime " + constDeclarationActual.getName() + " vec postoji u tabeli simbola! ", null);
        }
    }
    public void visit(NumberConst numberConst){
        constNum = numberConst.getVal();
        numberConst.struct = Tab.intType;
    }
    public void visit(BoolConst boolConst){
        constBool= boolConst.getVal()==1?true:false;
        boolConst.struct = new Struct(Struct.Bool);
    }
    public void visit(CharConst charConst){
        constChar = charConst.getVal();
        charConst.struct = Tab.charType;
    }
    public void visit(ConstDeclarationTypeListSemi constDeclarationTypeListSemi) {
        declarationTypeNode = null;
    }

    // obrada f-ja
    public void visit(MethodTypeName methodTypeName){
        currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethodName(), methodTypeName.getType().struct);
        methodTypeName.obj = currentMethod;
        Tab.openScope();
        report_info("Obradjuje se funkcija " + methodTypeName.getMethodName() + "(" + printObj(currentMethod) + ")", methodTypeName);
    }
    public void visit(MethodVoidName methodVoidName){
        currentMethod = Tab.insert(Obj.Meth, methodVoidName.getMethodName(), Tab.noType);
        methodVoidName.obj = currentMethod;
        if(methodVoidName.getMethodName().equals("main")){ mainMethodFound = true; }
        Tab.openScope();
        report_info("Obradjuje se funkcija " + methodVoidName.getMethodName() + "(" + printObj(currentMethod) + ")", methodVoidName);
    }
    public void visit(MethodDeclarationActual methodDeclarationActual) {
        if(!returnFound && currentMethod.getType() != Tab.noType){
            report_error("Semanticka greska na liniji " + methodDeclarationActual.getLine() +
                    ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);
        }
        currentMethod.setLevel(currentMethodFormParNum);
        Tab.chainLocalSymbols(currentMethod);
        Tab.closeScope();

        returnFound = false;
        currentMethod = null;
        currentMethodFormParNum = 0;
    }
    public void visit(FormalParamListActual formalParamListActual){
        currentMethodFormParNum++;
    }
    public void visit(ReturnStatementEmpty returnStatementEmpty){
        returnFound = true;
        Struct currMethType = currentMethod.getType();
        if(!currMethType.compatibleWith(Tab.noType)){
            report_error("Greska na liniji " + returnStatementEmpty.getLine() + " : "
                    + "return naredba mora biti bez izraza jer je f-ja " + currentMethod.getName() + " tipa void", null);
        }
    }
    public void visit(ReturnStatementExpression returnStatementExpression){
        returnFound = true;
        Struct currMethType = currentMethod.getType();
        if(!currMethType.compatibleWith(returnStatementExpression.getExpression().struct)
                && !((currMethType.getKind()==Struct.Bool)
                && (returnStatementExpression.getExpression().struct.getKind()==Struct.Bool))){
            report_error("Greska na liniji " + returnStatementExpression.getLine() + " : "
                    + "tip povratne vrednosti mora biti kompatibiln sa tipom f-je: " + currentMethod.getName() + "!", null);
        }
    }

    // obrada designator-a
    public void visit(DesignatorOnly designatorOnly){
        Obj obj = Tab.find(designatorOnly.getName());
        if(obj == Tab.noObj){
            report_error("Greska na liniji " + designatorOnly.getLine()+ " : ime "+designatorOnly.getName()+" nije deklarisano! ", null);
        }
        report_info("Koriscenje  " + designatorOnly.getName()  + "(" + printObj(obj) + ")" + " na liniji "+ designatorOnly.getLine(), null);
        designatorOnly.obj = obj;
    }
    public void visit(DesignatorArray designatorArray){
        designatorArray.obj = designatorArray.getArrayAccess().obj;
        if(designatorArray.obj == Tab.noObj){
            report_error("Greska na liniji " + designatorArray.getLine()+ " : ime "+designatorArray.obj.getName()+" nije deklarisano! ", null);
        } else if(designatorArray.getExpression().struct.getKind()!=Struct.Int) {
            report_error("Greska na liniji " + designatorArray.getLine()+ " : izraz za odredjivanje elementa niza nije tipa int! ", null);
        } else if(designatorArray.obj.getType().getKind()!=Struct.Array){
            report_error("Greska na liniji " + designatorArray.getLine()+ " : ime "+designatorArray.obj.getName()+" nije niz! ", null);
        }
        report_info("Koriscenje elementa niza " + designatorArray.obj.getName() + "(" + printObj(designatorArray.obj) + ")" + " na liniji "+ designatorArray.getLine(), null);
        designatorArray.obj = new Obj(Obj.Elem, designatorArray.obj.getName(), designatorArray.obj.getType().getElemType());
    }
    public void visit(ArrayAccess arrayAccess) {
        arrayAccess.obj = Tab.find(arrayAccess.getName());
    }
    public void visit(MethodCallActualParams methodCallActualParams){
        Obj func = methodCallActualParams.getDesignator().obj;
        if(Obj.Meth == func.getKind()){
            report_info("Pronadjen poziv funkcije " + func.getName() + "(" + printObj(func) + ")" + " na liniji " + methodCallActualParams.getLine(), null);
            methodCallActualParams.struct = func.getType();
        }else{
            report_error("Greska na liniji " + methodCallActualParams.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
            methodCallActualParams.struct = Tab.noType;
        }
        SyntaxNode node = methodCallActualParams.getParent();
        if(node.getClass() == FactorMethodCall.class && methodCallActualParams.struct == Tab.noType){
            report_error("Greska na liniji " + methodCallActualParams.getLine()+" : funkcija bez povratne vrednosti se ne moze koristiti u izrazima: "
                    + func.getName(), null);
        }
    }
    public void visit(MethodCallNoParams methodCallNoParams){
        Obj func = methodCallNoParams.getDesignator().obj;
        if(Obj.Meth == func.getKind()){
            report_info("Pronadjen poziv funkcije " + func.getName() + "(" + printObj(func) + ")" + " na liniji " + methodCallNoParams.getLine(), null);
            methodCallNoParams.struct = func.getType();
        }else{
            report_error("Greska na liniji " + methodCallNoParams.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
            methodCallNoParams.struct = Tab.noType;
        }
        SyntaxNode node = methodCallNoParams.getParent();
        if(node.getClass() == FactorMethodCall.class && methodCallNoParams.struct == Tab.noType){
            report_error("Greska na liniji " + methodCallNoParams.getLine()+" : funkcija bez povratne vrednosti se ne moze koristiti u izrazima: "
                    + func.getName(), null);
        }
    }
    public void visit(DesignatorStatementIncrement designatorStatementIncrement){
        if(!isIntType(designatorStatementIncrement.getDesignator().obj)){
            report_error("Greska na liniji " + designatorStatementIncrement.getLine()+" : designator " +
                    designatorStatementIncrement.getDesignator().obj.getName() + " nije tipa int!", null);
        } else {
            report_info("Pronadjeno inkrementiranje promenljive " + designatorStatementIncrement.getDesignator().obj.getName()
                    + "(" + printObj(designatorStatementIncrement.getDesignator().obj) + ")"
                    + " na liniji " + designatorStatementIncrement.getLine(), null);
        }
    }
    public void visit(DesignatorStatementDecrement designatorStatementDecrement){
        if(!isIntType(designatorStatementDecrement.getDesignator().obj)){
            report_error("Greska na liniji " + designatorStatementDecrement.getLine()+" : designator " +
                    designatorStatementDecrement.getDesignator().obj.getName() + " nije tipa int!", null);
        } else {
            report_info("Pronadjeno dekrementiranje promenljive " + designatorStatementDecrement.getDesignator().obj.getName()
                    + "(" + printObj(designatorStatementDecrement.getDesignator().obj) + ")"
                    + " na liniji " + designatorStatementDecrement.getLine(), null);
        }
    }

    //obrada izraza
    public void visit(ExpressionAddopLeftList expressionAddopLeftList){
        Struct te = expressionAddopLeftList.getExpression().struct;
        Struct t = expressionAddopLeftList.getTerm().struct;
        if(isIntType(t) && isIntType(te)){
            expressionAddopLeftList.struct = te;
        }else{
            report_error("Greska na liniji "+ expressionAddopLeftList.getLine()+" : nekompatibilni tipovi u izrazu za sabiranje.", null);
            expressionAddopLeftList.struct = Tab.noType;
        }
    }
    public void visit(ExpressionTermDef expressionTermDef){
        expressionTermDef.struct = expressionTermDef.getTermDef().struct;
    }
    public void visit(MinusTerm minusTerm){
        if(!isIntType(minusTerm.getTerm().struct)){
            report_error("Greska na liniji "+ minusTerm.getLine()+" : tip mora biti int", null);
            minusTerm.struct = Tab.noType;
        } else
        minusTerm.struct = minusTerm.getTerm().struct;
    }
    public void visit(OnlyTerm onlyTerm){
        onlyTerm.struct = onlyTerm.getTerm().struct;
    }
    public void visit(TermMullOpLeftList termMullOpLeftList){
        Struct te = termMullOpLeftList.getFactor().struct;
        Struct t = termMullOpLeftList.getTerm().struct;
        if(isIntType(t) && isIntType(te)){
            termMullOpLeftList.struct = te;
        }else{
            report_error("Greska na liniji "+ termMullOpLeftList.getLine()+" : nekompatibilni tipovi u izrazu za mnozenje.", null);
            termMullOpLeftList.struct = Tab.noType;
        }
    }
    public void visit(TermFactor termFactor){
        termFactor.struct = termFactor.getFactor().struct;
    }
    public void visit(FactorConst factorConst){
        factorConst.struct = factorConst.getConst().struct;
    }
    public void visit(FactorDesignator factorDesignator){
        factorDesignator.struct = factorDesignator.getDesignator().obj.getType().getKind()!=Struct.Array ?
                factorDesignator.getDesignator().obj.getType() : factorDesignator.getDesignator().obj.getType().getElemType();
    }
    public void visit(FactorMethodCall factorMethodCall){
        factorMethodCall.struct = factorMethodCall.getMethodCall().struct;
    }
    public void visit(FactorParenExoression factorParenExoression){
        factorParenExoression.struct = factorParenExoression.getExpression().struct;
    }
    public void visit(FactorNewTypeExpression factorNewTypeExpression){
        if(!isIntType(factorNewTypeExpression.getExpression().struct)){
            report_error("Greska na liniji "+ factorNewTypeExpression.getLine()+" : izraz izmedju [] mora biti tipa int.", null);
        }
        factorNewTypeExpression.struct = new Struct(Struct.Array, factorNewTypeExpression.getType().struct);
    }

    // obrada dodele vrednosti
    public void visit(LeftDesignatorAssignExpression leftDesignatorAssignExpression){
        if(!areAssignable(leftDesignatorAssignExpression.getLeftSideAssign().obj,
                leftDesignatorAssignExpression.getExpression().struct)){
            report_error("Greska na liniji " + leftDesignatorAssignExpression.getLine() + " : nije moguca dodela2!", null);
        }
    }
    public void visit(LeftSideAssignAssignOps leftSideAssignAssignOps) {
        if(!areAssignable(leftSideAssignAssignOps.getLeftSideAssign().obj,
                leftSideAssignAssignOps.getDesignator().obj)){
            report_error("Greska na liniji " + leftSideAssignAssignOps.getLine() + " : nije moguca dodela1!", null);
        }
        leftSideAssignAssignOps.obj = leftSideAssignAssignOps.getLeftSideAssign().obj;
    }
    public void visit(LeftSideAssignVar leftSideAssignVar){
        if(leftSideAssignVar.getDesignator().obj.getKind()!=Obj.Var
                && leftSideAssignVar.getDesignator().obj.getKind()!=Obj.Elem){
            report_error("Greska na liniji " + leftSideAssignVar.getLine() + " : nije moguca dodela necemu" +
                        "sto nije varijabla ili element niza!", null);
        }
        leftSideAssignVar.obj = leftSideAssignVar.getDesignator().obj;
    }

    // obrada vrste dodele
    public void visit(AddOpRightPlus addOpRightPlus){
        addOpRightPlus.obj = new Obj(Obj.NO_VALUE, "+=", Tab.noType);
    }
    public void visit(AddOpRightMinus addOpRightMinus){
        addOpRightMinus.obj = new Obj(Obj.NO_VALUE, "-=", Tab.noType);
    }
    public void visit(MulOpRightDiv mulOpRightDiv){
        mulOpRightDiv.obj = new Obj(Obj.NO_VALUE, "/=", Tab.noType);
    }
    public void visit(MulOpRightMod mulOpRightMod){
        mulOpRightMod.obj = new Obj(Obj.NO_VALUE, "%=", Tab.noType);
    }
    public void visit(MulOpRightMul mulOpRightMul){
        mulOpRightMul.obj = new Obj(Obj.NO_VALUE, "*=", Tab.noType);
    }
    public void visit(AssignOpAdd assignOpAdd){
        assignOpAdd.obj = assignOpAdd.getAddOpRight().obj;
    }
    public void visit(AssignOpMul assignOpMull){
        assignOpMull.obj = assignOpMull.getMulOpRight().obj;
    }
    public void visit(AssignOpEqual assignOpEqual){
        assignOpEqual.obj = new Obj(Obj.NO_VALUE, "=", Tab.noType);
    }

    // obrada uslova
    public void visit(ConditionFactList conditionFactList){
        if(!conditionFactList.getExpression().struct.compatibleWith(conditionFactList.getExpression1().struct)
            && !((conditionFactList.getExpression().struct.getKind()==Struct.Bool)
                    && (conditionFactList.getExpression1().struct.getKind()==Struct.Bool))){
            report_error("Greska na liniji " + conditionFactList.getLine()+" : nisu kompatibilni tipovi", null);
        } else {
          if(conditionFactList.getExpression().struct.equals(new Struct(Struct.Array))
                && conditionFactList.getRelOp().obj != null) {
              report_error("Greska na liniji " + conditionFactList.getLine()+
                      " : nizovi se mogu uporedjivati samo sa == ili !=", null);
          }
        }
    }
    public void visit(RelOpEquals relOpEquals){
        relOpEquals.obj = new Obj(-1, "==", Tab.noType);
    }
    public void visit(RelOpNotEquals relOpNotEquals){
        relOpNotEquals.obj = new Obj(-1, "!=", Tab.noType);
    }
    public void visit(ConditionFactExpression conditionFactExpression){
        if(!isBoolType(conditionFactExpression.getExpression().struct)){
            report_error("Greska na liniji " + conditionFactExpression.getLine()+
                    " : izraz mora biti tipa bool", null);
        }
    }

    // obrada print i read f-ja
    public void visit(ReadStatement readStatement){
        if(!isPrintableOrReadable(readStatement.getDesignator().obj.getType())) {
            report_error("Greska na liniji " + readStatement.getLine() + " : promenljiva " +
                    readStatement.getDesignator().obj.getName() + " nije odgovarajuceg tipa!", null);
        } else if(readStatement.getDesignator().obj.getKind()!=Obj.Var
                    && readStatement.getDesignator().obj.getKind()!= Obj.Elem){
            report_error("Greska na liniji " + readStatement.getLine() + " : designator " +
                    readStatement.getDesignator().obj.getName() + " nije promenljiva ili element niza!", null);
        } else {
            report_info("Pronadjen poziv funkcije read na liniji " + readStatement.getLine(), null);
        }
    }
    public void visit(PrintExpressionStatement printExpressionStatement){
        if(!isPrintableOrReadable(printExpressionStatement.getExpression().struct)) {
            report_error("Greska na liniji " + printExpressionStatement.getLine() + " : izraz " +
                    printExpressionStatement + " nije odgovarajuceg tipa!", null);
        } else {
            report_info("Pronadjen poziv funkcije print na liniji " + printExpressionStatement.getLine(), null);
        }
    }
    public void visit(PrintExpressionNumStatement printExpressionNumStatement){
        if(!isPrintableOrReadable(printExpressionNumStatement.getExpression().struct)) {
            report_error("Greska na liniji " + printExpressionNumStatement.getLine() + " : izraz " +
                    printExpressionNumStatement + " nije odgovarajuceg tipa!", null);
        } else {
            report_info("Pronadjen poziv funkcije print na liniji " + printExpressionNumStatement.getLine(), null);
        }
    }

    public void visit(Type type){
        isTypeValid(type);
    }

    // pomocne f-je
    public boolean isTypeValid(Type type){
        Obj typeNode = Tab.find(type.getTypeName());
        if(typeNode == Tab.noObj){
            report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
            type.struct = Tab.noType;
            return false;
        }else{
            if(Obj.Type == typeNode.getKind()){
                type.struct = typeNode.getType();
                return true;
            }else{
                report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
                type.struct = Tab.noType;
                return false;
            }
        }
    }
    public boolean tableHasIdent(String name){
        Obj varExist = Tab.find(name);
        if(varExist != Tab.noObj){
            return true;
        }
        return false;
    }
    public boolean isIntType(Obj obj){
        return (obj.getType().getKind()==Struct.Int)
               || ((obj.getType().getKind()==Struct.Array)
                    && (obj.getType().getElemType().getKind()==Struct.Int));
    }
    public boolean isIntType(Struct str){
        return (str.getKind()==Struct.Int)
                || ((str.getKind()==Struct.Array)
                && (str.getElemType().getKind()==Struct.Int));
    }
    public boolean isBoolType(Struct str){
        return (str.getKind()==Struct.Bool)
                || ((str.getKind()==Struct.Array)
                && (str.getElemType().getKind()==Struct.Bool));
    }
    public boolean isPrintableOrReadable(Struct str){
        return (str.getKind()==Struct.Int)
                || (str.getKind()==Struct.Char)
                || (str.getKind()==Struct.Bool)
                || ((str.getKind()==Struct.Array)
                    && ((str.getElemType().getKind()==Struct.Int)
                        || (str.getElemType().getKind()==Struct.Bool)
                        || (str.getElemType().getKind()==Struct.Char)));
    }
    public boolean areAssignable(Obj obj, Struct str){
        return str.assignableTo(obj.getType())
                || (obj.getType().getKind()==Struct.Array && str==Tab.nullType)
                || (obj.getType().getKind()==Struct.Array && str.getKind()==Struct.Array
                    && str.getElemType().getKind()==obj.getType().getElemType().getKind())
                || (obj.getType().getKind()==Struct.Bool && str.getKind()==Struct.Bool);
    }
    public boolean areAssignable(Obj obj1, Obj obj2){
        return obj2.getType().assignableTo(obj1.getType())
                || (obj1.getType().getKind()==Struct.Array && obj2.getType()==Tab.nullType)
                || (obj1.getType().getKind()==Struct.Array && obj2.getType().getKind()==Struct.Array
                    && obj2.getType().getElemType().getKind()==obj1.getType().getElemType().getKind())
                || (obj1.getType().getKind()==Struct.Bool && obj2.getType().getKind()==Struct.Bool);
    }
    public boolean passed(){
        return !errorDetected && mainMethodFound;
    }
}
