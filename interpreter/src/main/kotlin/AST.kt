import java.lang.Error

class AST(
    private val head: NodeToken,
    private val left: IAST,
    private val right: IAST
) : IAST {

    override fun equalsTree(tree: IAST): Boolean {
        return if (tree is AST) {
            tree.head === head && left.equalsTree(tree.left) && right.equalsTree(tree.right)
        } else {
            false
        }
    }

    override fun operate(memory: Memory): ExpressionResult {
        return when(head) {
            is NodeToken.ASSIGNATION -> {
                val variableDeclaration = left.operate(memory)
                val value = right.operate(memory)
                if(
                    variableDeclaration is ExpressionResult.VariableDeclaration &&
                    (value is ExpressionResult.StringResult || value is ExpressionResult.NumberResult)
                ) {
                    val availableType = when(variableDeclaration.type) {
                        is Type.StringType -> {
                            if(value is ExpressionResult.StringResult) {
                                AvailableTypes.String(value.string)
                            } else {
                                throw Error()
                            }
                        }
                        is Type.NumberType -> {
                             if(value is ExpressionResult.NumberResult) {
                                 AvailableTypes.Number(value.value)
                             } else {
                                 throw Error()
                             }
                        }
                    }
                    return ExpressionResult.VariableAssignation(
                        variableDeclaration.name,
                        availableType
                    )
                } else {
                    throw Error("invalid assignation")
                }
            }
            is NodeToken.DECLARATION -> {
                if(
                    left is Leaf &&
                    right is Leaf &&
                    left.getValue() is LeafToken.IDENTIFIER &&
                    right.getValue() is LeafToken.TYPE
                ) {
                    return ExpressionResult.VariableDeclaration(
                        (left.getValue() as LeafToken.IDENTIFIER).value,
                        (right.getValue() as LeafToken.TYPE).type
                    )
                } else {
                    throw Error("invalid declaration")
                }
            }
            is NodeToken.OPERATOR -> {
                return head.operator.operate(memory, left.operate(memory), right.operate(memory))
            }
        }
    }

}

