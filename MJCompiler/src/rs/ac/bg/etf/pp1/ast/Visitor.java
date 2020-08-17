// generated with ast extension for cup
// version 0.8
// 18/7/2020 0:13:41


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(DeclarationList DeclarationList);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(MethodCall MethodCall);
    public void visit(MethodDeclaration MethodDeclaration);
    public void visit(Var Var);
    public void visit(ConditionOptional ConditionOptional);
    public void visit(AssignOps AssignOps);
    public void visit(StatementList StatementList);
    public void visit(MethodDeclarationList MethodDeclarationList);
    public void visit(LeftSideAssign LeftSideAssign);
    public void visit(ConditionTerm ConditionTerm);
    public void visit(MulOpLeft MulOpLeft);
    public void visit(Factor Factor);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(AddOpRight AddOpRight);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(DesignatorStatementList DesignatorStatementList);
    public void visit(RelOp RelOp);
    public void visit(ActualParams ActualParams);
    public void visit(ElseStatement ElseStatement);
    public void visit(FormalParamList FormalParamList);
    public void visit(FormalParams FormalParams);
    public void visit(VarDeclarationList VarDeclarationList);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Const Const);
    public void visit(ConditionFact ConditionFact);
    public void visit(Statement Statement);
    public void visit(Expression Expression);
    public void visit(AddOpLeft AddOpLeft);
    public void visit(MulOpRight MulOpRight);
    public void visit(Declaration Declaration);
    public void visit(ConstDeclarationList ConstDeclarationList);
    public void visit(TermDef TermDef);
    public void visit(AssignOpMul AssignOpMul);
    public void visit(AssignOpAdd AssignOpAdd);
    public void visit(AssignOpEqual AssignOpEqual);
    public void visit(AssignOp AssignOp);
    public void visit(MulOpRightMod MulOpRightMod);
    public void visit(MulOpRightDiv MulOpRightDiv);
    public void visit(MulOpRightMul MulOpRightMul);
    public void visit(MulOpLeftMod MulOpLeftMod);
    public void visit(MulOpLeftDiv MulOpLeftDiv);
    public void visit(MulOpLeftMul MulOpLeftMul);
    public void visit(AddOpRightMinus AddOpRightMinus);
    public void visit(AddOpRightPlus AddOpRightPlus);
    public void visit(AddOpLeftMinus AddOpLeftMinus);
    public void visit(AddOpLeftPlus AddOpLeftPlus);
    public void visit(RelOpGreaterEquals RelOpGreaterEquals);
    public void visit(RelOpGreater RelOpGreater);
    public void visit(RelOpLessEqual RelOpLessEqual);
    public void visit(RelOpLess RelOpLess);
    public void visit(RelOpNotEquals RelOpNotEquals);
    public void visit(RelOpEquals RelOpEquals);
    public void visit(FactorNewTypeExpression FactorNewTypeExpression);
    public void visit(FactorParenExoression FactorParenExoression);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(FactorMethodCall FactorMethodCall);
    public void visit(FactorConst FactorConst);
    public void visit(TermFactor TermFactor);
    public void visit(TermMullOpLeftList TermMullOpLeftList);
    public void visit(OnlyTerm OnlyTerm);
    public void visit(MinusTerm MinusTerm);
    public void visit(ExpressionTermDef ExpressionTermDef);
    public void visit(ExpressionAddopLeftList ExpressionAddopLeftList);
    public void visit(ConditionFactExpression ConditionFactExpression);
    public void visit(ConditionFactList ConditionFactList);
    public void visit(ConditionLastFact ConditionLastFact);
    public void visit(ConditionListFact ConditionListFact);
    public void visit(ConditionLastTerm ConditionLastTerm);
    public void visit(ConditionListTerm ConditionListTerm);
    public void visit(NoConditionOptional NoConditionOptional);
    public void visit(ForStatementError ForStatementError);
    public void visit(ConditionOptionalCondition ConditionOptionalCondition);
    public void visit(ActualParamsExpression ActualParamsExpression);
    public void visit(ActualParamsList ActualParamsList);
    public void visit(MethodCallNoParams MethodCallNoParams);
    public void visit(MethodCallActualParams MethodCallActualParams);
    public void visit(NoDesignatorStatementList NoDesignatorStatementList);
    public void visit(DesignatorStatementOnly DesignatorStatementOnly);
    public void visit(ArrayAccess ArrayAccess);
    public void visit(DesignatorOnly DesignatorOnly);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(LeftSideAssignVar LeftSideAssignVar);
    public void visit(LeftSideAssignAssignOps LeftSideAssignAssignOps);
    public void visit(DesignatorStatementMethodCall DesignatorStatementMethodCall);
    public void visit(DesignatorStatementDecrement DesignatorStatementDecrement);
    public void visit(DesignatorStatementIncrement DesignatorStatementIncrement);
    public void visit(LeftDesignatorAssignExpression LeftDesignatorAssignExpression);
    public void visit(NoElseStatement NoElseStatement);
    public void visit(ElseStatementStatement ElseStatementStatement);
    public void visit(ErrorStatement ErrorStatement);
    public void visit(CurlyBraceStatement CurlyBraceStatement);
    public void visit(ReturnStatementExpression ReturnStatementExpression);
    public void visit(ReturnStatementEmpty ReturnStatementEmpty);
    public void visit(ContinueStatement ContinueStatement);
    public void visit(BrakStatement BrakStatement);
    public void visit(ForeachStatement ForeachStatement);
    public void visit(ForStatement ForStatement);
    public void visit(PrintExpressionNumStatement PrintExpressionNumStatement);
    public void visit(PrintExpressionStatement PrintExpressionStatement);
    public void visit(ReadStatement ReadStatement);
    public void visit(DesignatorStatementStatement DesignatorStatementStatement);
    public void visit(IfStatement IfStatement);
    public void visit(NoStatementList NoStatementList);
    public void visit(StatementListStatement StatementListStatement);
    public void visit(FormalParamVar FormalParamVar);
    public void visit(FormalParamListActual FormalParamListActual);
    public void visit(FormalParamErrorParen FormalParamErrorParen);
    public void visit(FormalParamErrorComma FormalParamErrorComma);
    public void visit(NoFormalParamsList NoFormalParamsList);
    public void visit(FormalParamsList FormalParamsList);
    public void visit(MethodDeclarationActual MethodDeclarationActual);
    public void visit(MethodVoidName MethodVoidName);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(VoidMethodDeclaration VoidMethodDeclaration);
    public void visit(TypeMethodDeclaration TypeMethodDeclaration);
    public void visit(NoMethodDeclarationList NoMethodDeclarationList);
    public void visit(MethodDeclarationListMethod MethodDeclarationListMethod);
    public void visit(CharConst CharConst);
    public void visit(BoolConst BoolConst);
    public void visit(NumberConst NumberConst);
    public void visit(ConstDeclarationActual ConstDeclarationActual);
    public void visit(ConstDeclError ConstDeclError);
    public void visit(ConstDecl ConstDecl);
    public void visit(ConstDeclarationListComaActual ConstDeclarationListComaActual);
    public void visit(ConstDeclarationError ConstDeclarationError);
    public void visit(ConstDeclarationTypeListSemi ConstDeclarationTypeListSemi);
    public void visit(Type Type);
    public void visit(IdentArray IdentArray);
    public void visit(Ident Ident);
    public void visit(VarDeclError VarDeclError);
    public void visit(VarDecl VarDecl);
    public void visit(VarDeclarationListVar VarDeclarationListVar);
    public void visit(VarType VarType);
    public void visit(VarDeclarationError VarDeclarationError);
    public void visit(VarDeclarationV VarDeclarationV);
    public void visit(DeclarationListConst DeclarationListConst);
    public void visit(DeclarationListVar DeclarationListVar);
    public void visit(NoDeclarationList NoDeclarationList);
    public void visit(DeclarationListDeclaration DeclarationListDeclaration);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
