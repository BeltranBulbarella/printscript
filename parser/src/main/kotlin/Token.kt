

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

sealed class UtilToken : Token {
    object LET_KEY_WORD : UtilToken()
    object SEMICOLON : UtilToken()
}
