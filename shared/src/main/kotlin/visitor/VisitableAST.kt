package visitor

interface VisitableAST {
    fun accept(visitor: Visitor): VisitableAST
}

class AssignationAST(
    val declaration: VisitableAST,
    val expression: VisitableAST
) : VisitableAST {
    override fun accept(visitor: Visitor) = visitor.visitAssignationAST(this)
}

class DeclarationAST(
    val variableName: String,
    val variableType: Types
) : VisitableAST {
    override fun accept(visitor: Visitor) = visitor.visitDeclarationAST(this)
}

class BinaryOperationAST(
    val left: VisitableAST,
    val right: VisitableAST,
    val operation: Operation
) : VisitableAST {
    override fun accept(visitor: Visitor) = visitor.visitBinaryOperationAST(this)
}

class UnaryOperationAST(
    val function: Function,
    val args: VisitableAST
) : VisitableAST {
    override fun accept(visitor: Visitor) = visitor.visitUnaryOperationAST(this)
}

class LiteralAST(
    val value: Value
) : VisitableAST {
    override fun accept(visitor: Visitor) = visitor.visitLiteralAST(this)
}

class VariableAST(
    val variableName: String
) : VisitableAST {
    override fun accept(visitor: Visitor) = visitor.visitVariableAST(this)
}

class EmptyAST() : VisitableAST {
    override fun accept(visitor: Visitor) = visitor.visitEmptyAST(this)
}

sealed interface Operation
object SUM : Operation
object SUBTRACT : Operation
object DIVIDE : Operation
object MULTIPLY : Operation

sealed interface Types
object NUM: Types
object STR: Types

sealed interface Value
class StrValue(val value: String): Value
class NumValue(val value: Double): Value

sealed interface Function
object PRINT : Function