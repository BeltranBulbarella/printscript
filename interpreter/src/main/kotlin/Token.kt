
sealed interface Token

sealed class NodeToken : Token {
    object ASSIGNATION : NodeToken()

    object DECLARATION : NodeToken()

    data class OPERATOR(val operator: Operator) : NodeToken()

}

sealed class LeafToken : Token {
    data class IDENTIFIER(val value: String) : LeafToken()
    data class TYPE(val type: Type) : LeafToken()

    data class LITERAL(val literal: AvailableTypes) : LeafToken()
}

sealed class ExpressionResult {

    class VariableAssignation(val name: String, val value: AvailableTypes) : ExpressionResult()
    class VariableDeclaration(val name: String, val type: Type) : ExpressionResult()
    class StringResult(val string: String) : ExpressionResult()
    class NumberResult(val value: Number) : ExpressionResult()

}