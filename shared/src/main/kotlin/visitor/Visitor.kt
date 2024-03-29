package visitor

interface Visitor {
    fun visitAssignationAST(ast: AssignationAST): VisitableAST
    fun visitDeclarationAST(ast: DeclarationAST): VisitableAST
    fun visitBinaryOperationAST(ast: BinaryOperationAST): VisitableAST
    fun visitUnaryOperationAST(ast: UnaryOperationAST): VisitableAST
    fun visitLiteralAST(ast: LiteralAST): VisitableAST
    fun visitVariableAST(ast: VariableAST): VisitableAST
    fun visitEmptyAST(ast: EmptyAST): VisitableAST
}